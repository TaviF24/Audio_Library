package org.example.CommandInterface;

import org.example.Commands.AbstractCommand;
import org.example.Commands.Invoker;
import org.example.Commands.QuitCommand;
import org.example.Data.CommandForTable;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.AudioLibraryRuntimeException;
import org.example.Exceptions.NoPermissionException;
import org.example.InputConverter;
import org.example.Data.Users.User;
import org.example.Printer;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CommandInterface {
    private static CommandInterface INSTANCE = null;

    private CommandInterface(){}

    public static CommandInterface getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new CommandInterface();
        return INSTANCE;
    }

    public void listenForCommands(Reader reader){

        BufferedReader buf = new BufferedReader(reader);
        CommandFormat commandFormat = getInputCommand(buf);

        AbstractCommand command = null;
        try{
            command = InputConverter.mapCommand(commandFormat.getCommand(), commandFormat.getArgs());
        }catch (AudioLibraryRuntimeException e){
            System.out.println(e.getMessage());
        }
        while(!(command instanceof QuitCommand)){
            try{
                if(command != null && command.getAllowedTypeUser().contains(Session.getSessionUser().getUserTypes())){
                    runCommand(command, commandFormat);
                }else{
                    if(command != null /*
                            && command.getAllowedTypeUser().size() == 1
                            && command.getAllowedTypeUser().get(0) != UserTypes.ANONYMOUS */
                            && !command.getAllowedTypeUser().contains(Session.getSessionUser().getUserTypes())){
                        saveCommand(Session.getSessionUser(), commandFormat.toString(), false);
                        throw new NoPermissionException();
                    }
                }
            }catch (AudioLibraryRuntimeException e){
                System.out.println(e.getMessage());
            }

            try{
                commandFormat = getInputCommand(buf);
                command = InputConverter.mapCommand(commandFormat.getCommand(), commandFormat.getArgs());
            }catch (AudioLibraryRuntimeException e){
                System.out.println(e.getMessage());
                command = null;
            }

        }
        runCommand(command, commandFormat); //for quit command

    }

    private void saveCommand(User user, String command, Boolean flag){
//        DBWrapper<CommandForTable> dbWrapper = new DBWrapper<>();
        DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
        dbWrapper.saveCommand(user, command, flag);

//        String selectFromDB = """
//                SELECT id FROM Users WHERE username = ?;
//                """;
//        try(
//                Connection connection = DriverManager.getConnection(Credits.getConnectionCredits().path(), Credits.getConnectionCredits().user(), Credits.getConnectionCredits().password());
//                PreparedStatement preparedStatement = connection.prepareStatement(selectFromDB)
//        ){
//            int id;
//            preparedStatement.setString(1, user.getUsername());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                id = resultSet.getInt("id");
//                DBWrapper<CommandForTable>dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
//                dbWrapper.insertIntoDB(new CommandForTable(command, id, flag));
//            }
//        }
//        catch (SQLException e){
//            System.out.println(e);
//        }
    }

    public void runCommand(AbstractCommand command, CommandFormat commandNameAndArgs){
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        boolean status = false;
        try{
            status = invoker.runCommand();
        }catch (AudioLibraryRuntimeException e){
            System.out.println(e.getMessage());
        }
        Printer.printStatusMessage(command, status);

        saveCommand(Session.getSessionUser(), commandNameAndArgs.toString(), status);
    }

    public CommandFormat getInputCommand(BufferedReader bufferedReader){

        try{
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String commandString = "";
            String[] args = new String[0];
            if(stringTokenizer.hasMoreTokens()) {
                commandString = stringTokenizer.nextToken();
            }
            String s;
            if(stringTokenizer.hasMoreTokens()){
                s = stringTokenizer.nextToken("");
                args = s.strip().split("\\s");
            }
            boolean inQuotes = false;
            ArrayList<String> result = new ArrayList<>();
            ArrayList<String> insideQuotes = new ArrayList<>();

            for(int i=0;i<args.length;i++){
                if(args[i].length()>1 && args[i].charAt(0) == '"' && args[i].charAt(args[i].length()-1) == '"' && !inQuotes){
                    args[i] = args[i].substring(1,args[i].length()-1);
                }else{
                    if(!args[i].isEmpty() && args[i].charAt(0) == '"' && !inQuotes){
                        inQuotes = true;
                    }else{
                        if(!args[i].isEmpty() && args[i].charAt(args[i].length()-1) == '"' && inQuotes){
                            insideQuotes.add(args[i]);
                            String s1 = insideQuotes.get(0).substring(1);
                            for(int j=1; j<insideQuotes.size()-1; j++){
                                s1 += " " + insideQuotes.get(j);
                            }
                            s1 += " " + insideQuotes.get(insideQuotes.size()-1).substring(0, insideQuotes.get(insideQuotes.size()-1).length()-1);
                            result.add(s1);
                            inQuotes = false;
                            insideQuotes.clear();
                            continue;
                        }
                    }
                }
                if(inQuotes){
                    insideQuotes.add(args[i]);
                }else{
                    if(!args[i].isEmpty()){
                        result.add(args[i]);
                    }
                }
            }
            result.addAll(insideQuotes);
            return new CommandFormat(commandString, result.toArray(new String[0]));

        }catch (IOException e){
            System.err.println("System error\n" + e);
        }

        return new CommandFormat();
    }

}
