package org.example.Exceptions.Unchecked;

public class InvalidTypeOfParameterException extends AudioLibraryUncheckedException {
    public InvalidTypeOfParameterException(String command) {
        super("Invalid usage of command " + command);
    }
}
