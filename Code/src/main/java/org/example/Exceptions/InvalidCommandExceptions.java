package org.example.Exceptions;

public class InvalidCommandExceptions extends RuntimeException{
    public InvalidCommandExceptions() {
        super("Unknown command");
    }
}
