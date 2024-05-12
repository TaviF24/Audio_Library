package org.example.Exceptions;

public class InvalidLoginException extends AudioLibraryRuntimeException{
    public InvalidLoginException() {
        super("Username or password is invalid. Please try again!");
    }
}
