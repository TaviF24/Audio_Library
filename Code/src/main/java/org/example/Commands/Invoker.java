package org.example.Commands;

import org.example.Exceptions.Checked.AudioLibraryCheckedException;

public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    /**
     * Executes the currently set command.
     *
     * @return {@code true} if the command is executed successfully, {@code false} otherwise
     * @throws AudioLibraryCheckedException if an error occurs during command execution
     */
    public boolean runCommand() throws AudioLibraryCheckedException {
        return command.execute();
    }

}
