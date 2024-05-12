package org.example.Exceptions;

public class InvalidTypeOfParameterException extends AudioLibraryRuntimeException{
    public InvalidTypeOfParameterException(String command) {
        super("Invalid usage of command " + command);
    }
}
