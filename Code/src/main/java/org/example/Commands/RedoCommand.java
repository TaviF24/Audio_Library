package org.example.Commands;

import org.example.CommandInterface.CommandFormat;
import org.example.Data.CommandForTable;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Exceptions.Unchecked.InvisibleRedoException;
import org.example.Exceptions.Unchecked.NoPermissionException;
import org.example.Exceptions.Unchecked.UnknownCommandException;
import org.example.InputConverter;
import org.example.Session;
import org.example.Utils.InputParser;
import org.example.Utils.UserTypes;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class RedoCommand extends AbstractCommand{
    public RedoCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ADMIN);
    }

    @Override
    public boolean execute() throws AudioLibraryCheckedException {
        
        int commandId = Integer.parseInt(getArgs()[0]);
        DBWrapper<CommandForTable> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        CommandForTable command = new CommandForTable(commandId, 0, "", false);
        String[] forCheck = new String[]{"id"};
        String[] forGet = new String[]{"command"};
        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(command, forCheck, forGet);
        if(arrayList.isEmpty()){
            throw new UnknownCommandException();
        }
        String input = arrayList.get(0).toString();
        StringTokenizer stringTokenizer = new StringTokenizer(input, " ");
        String commandString = "";
        if(stringTokenizer.hasMoreTokens()) {
            commandString = stringTokenizer.nextToken();
        }
        String s = ""; 
        if(stringTokenizer.hasMoreTokens()){
            s = stringTokenizer.nextToken("");
        }
        ArrayList<String> args = InputParser.getArgs(s);
        CommandFormat commandFormat = new CommandFormat(commandString, args.toArray(new String[0]));
        commandFormat.setOriginalCommand(input);
        AbstractCommand abstractCommand = InputConverter.mapCommand(commandFormat);
        if(abstractCommand == null){
            throw new UnknownCommandException();
        }
        if(!abstractCommand.getAllowedTypeUser().contains(Session.getSessionUser().getUserTypes())){
            throw new NoPermissionException();
        }
        if(abstractCommand instanceof RedoCommand && commandId == Integer.parseInt(args.get(0))){
            return false;
        }
        if(abstractCommand instanceof QuitCommand){
            throw new InvisibleRedoException();
        }
        boolean status = abstractCommand.execute();
        setSuccessMessage(abstractCommand.getSuccessMessage());
        return status;
    }
}
