package org.example.Exceptions.Unchecked;

public class InvalidLoginException extends AudioLibraryUncheckedException {
    public InvalidLoginException() {
        super("Username or password is invalid. Please try again!");
    }
}
