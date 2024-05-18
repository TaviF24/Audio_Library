package org.example.CommandInterface;

import org.example.Commands.AbstractCommand;
import org.example.Commands.Invoker;
import org.example.Commands.QuitCommand;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Exceptions.Unchecked.AudioLibraryUncheckedException;
import org.example.Exceptions.Unchecked.CommandOrArgsTooLongException;
import org.example.Exceptions.Unchecked.InvisibleRedoException;
import org.example.Exceptions.Unchecked.NoPermissionException;
import org.example.InputConverter;
import org.example.Data.Users.User;
import org.example.Utils.Printer;
import org.example.Session;
import org.example.Utils.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CommandInterface {
    private static CommandInterface INSTANCE = null;

    private CommandInterface(){}

    public static CommandInterface getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new CommandInterface();
        return INSTANCE;
    }

    /**
     * Listens for and processes commands from the given reader.
     * <p>
     * This method reads commands from the provided {@link Reader}, maps them to corresponding command objects,
     * and executes them if permitted by the session user. The loop continues until a quit command is encountered.
     * </p>
     *
     * @param reader the source from which commands are read
     */
    public void listenForCommands(Reader reader){

        AbstractCommand command = null;

        BufferedReader buf = new BufferedReader(reader);
        CommandFormat commandFormat = getInputCommand(buf);

        try{
            command = InputConverter.mapCommand(commandFormat);
        }catch (AudioLibraryUncheckedException e){
            System.out.println(e.getMessage());
        }
        while(!(command instanceof QuitCommand)){
            try{
                if(command != null && command.getAllowedTypeUser().contains(Session.getSessionUser().getUserTypes())){
                    runCommand(command, commandFormat);
                }else{
                    if(command != null && !command.getAllowedTypeUser().contains(Session.getSessionUser().getUserTypes())){
                        saveCommand(Session.getSessionUser(), commandFormat.getOriginalCommand(), false);
                        throw new NoPermissionException();
                    }
                }
            }
            catch (InvisibleRedoException ignored){
                command = new QuitCommand(commandFormat.getArgs());
                break;
            }
            catch (AudioLibraryUncheckedException e){
                System.out.println(e.getMessage());
            }

            try{
                commandFormat = getInputCommand(buf);
                command = InputConverter.mapCommand(commandFormat);
            }catch (AudioLibraryUncheckedException e){
                System.out.println(e.getMessage());
                command = null;
            }

        }
        runCommand(command, commandFormat); //for quit command

    }

    private void saveCommand(User user, String command, Boolean flag){
        DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
        dbWrapper.saveCommand(user, command, flag);
    }

    private void runCommand(AbstractCommand command, CommandFormat commandNameAndArgs){
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        boolean status = false;
        try{
            status = invoker.runCommand();
        }catch (InvisibleRedoException ignored){
            throw new InvisibleRedoException();
        }
        catch (AudioLibraryUncheckedException | AudioLibraryCheckedException e){
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e){
            System.out.println("Error\n" + e);
        }
        Printer.printStatusMessage(command, status);

        saveCommand(Session.getSessionUser(), commandNameAndArgs.getOriginalCommand(), status);
    }

    private CommandFormat getInputCommand(BufferedReader bufferedReader){

        try{
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String commandString = "";
            if(stringTokenizer.hasMoreTokens()) {
                commandString = stringTokenizer.nextToken();
            }
            if(commandString.length() > 254){
                throw new CommandOrArgsTooLongException();
            }
            String s="";
            String originalCommand = commandString;

            if(stringTokenizer.hasMoreTokens()){
                s = stringTokenizer.nextToken("");
                originalCommand += s;
            }
            ArrayList<String> args = InputParser.getArgs(s);
            for (String arg : args) {
                if(arg.length() > 254){
                    throw new CommandOrArgsTooLongException();
                }
            }
            CommandFormat commandFormat = new CommandFormat(commandString, args.toArray(new String[0]));
            commandFormat.setOriginalCommand(originalCommand);
            return commandFormat;

        }catch (IOException e){
            System.err.println("System error\n" + e);
        }

        return new CommandFormat();
    }

}
