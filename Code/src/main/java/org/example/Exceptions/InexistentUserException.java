package org.example.Exceptions;

public class InexistentUserException extends AudioLibraryRuntimeException{
    public InexistentUserException() {
        super("Specified user does not exist!");
    }
}
