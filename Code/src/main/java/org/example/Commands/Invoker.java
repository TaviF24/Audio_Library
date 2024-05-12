package org.example.Commands;

import org.example.Data.CommandForTable;
import org.example.Data.Users.User;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Printer;

public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public boolean runCommand(){
        return command.execute();
    }

}
