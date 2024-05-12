package org.example.Exceptions;

public class InvalidNumberOfParametersException extends AudioLibraryRuntimeException{

    public InvalidNumberOfParametersException(String command) {
        super("Invalid usage of command " + command);
    }
}
