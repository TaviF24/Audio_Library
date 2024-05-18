package org.example.Commands;

import org.example.Exceptions.Checked.AudioLibraryCheckedException;

public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public boolean runCommand() throws AudioLibraryCheckedException {
        return command.execute();
    }

}
