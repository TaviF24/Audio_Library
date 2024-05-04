package org.example.CommandInterface;

public class CommandFormat {
    private final String command;
    private final String[] args;

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
}
