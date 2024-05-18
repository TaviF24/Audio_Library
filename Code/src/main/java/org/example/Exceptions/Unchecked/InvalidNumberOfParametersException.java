package org.example.Exceptions.Unchecked;

public class InvalidNumberOfParametersException extends AudioLibraryUncheckedException {

    public InvalidNumberOfParametersException(String command) {
        super("Invalid usage of command " + command);
    }
}
