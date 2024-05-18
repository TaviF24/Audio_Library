package org.example.CommandInterface;

import java.util.Arrays;

public class CommandFormat {
    private final String command;
    private final String[] args;
    private String originalCommand;

    public CommandFormat() {
        command = "";
        args = new String[0];
    }

    public CommandFormat(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String getOriginalCommand() {
        return originalCommand;
    }

    public void setOriginalCommand(String originalCommand) {
        this.originalCommand = originalCommand;
    }

    @Override
    public String toString() {
        StringBuilder argsString = new StringBuilder();
        for (String arg : args) {
            argsString.append(arg).append(' ');
        }

        return command+ " " + argsString;
    }
}
