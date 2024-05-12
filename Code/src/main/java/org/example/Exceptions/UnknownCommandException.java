package org.example.Exceptions;

public class UnknownCommandException extends AudioLibraryRuntimeException{

    public UnknownCommandException() {
        super("Command unknown!");
    }
}
