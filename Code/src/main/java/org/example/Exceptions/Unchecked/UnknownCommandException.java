package org.example.Exceptions.Unchecked;

public class UnknownCommandException extends AudioLibraryUncheckedException {

    public UnknownCommandException() {
        super("Command unknown!");
    }
}
