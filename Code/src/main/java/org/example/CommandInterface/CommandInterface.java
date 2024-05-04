package org.example.CommandInterface;

import org.example.Commands.AbstractCommand;
import org.example.Commands.Invoker;
import org.example.Commands.QuitCommand;
import org.example.InputConverter;
import org.example.Users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommandInterface {
    private static CommandInterface INSTANCE = null;

    private CommandInterface(){}

    public static CommandInterface getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new CommandInterface();
        return INSTANCE;
    }

    public void listenForCommands(User givenByUser){

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(inputStreamReader);
        CommandFormat commandFormat = getInputCommand(buf);
        User currentUser = givenByUser;

        AbstractCommand command = InputConverter.mapCommand(commandFormat.getCommand(), commandFormat.getArgs());
        while(command == null || !(command instanceof QuitCommand)){

            if(command != null && currentUser.getUserTypes() == command.getAllowedTypeUser()){
                runCommand(currentUser, command);
            }

            commandFormat = getInputCommand(buf);
            command = InputConverter.mapCommand(commandFormat.getCommand(), commandFormat.getArgs());
        }

    }

    public void runCommand(User user, AbstractCommand command){
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.runCommand();
    }

    public CommandFormat getInputCommand(BufferedReader bufferedReader){

        try{

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String commandString = "";
            String[] args = new String[0];
            if(stringTokenizer.hasMoreTokens()) {
                commandString = stringTokenizer.nextToken();
            }
            if(stringTokenizer.hasMoreTokens()){
                args = stringTokenizer.nextToken("").strip().split("\\s+");
            }

            return new CommandFormat(commandString, args);

        }catch (IOException ignored){}

        return new CommandFormat();
    }

}
