package org.example.Exceptions.Unchecked;

public class InexistentUserException extends AudioLibraryUncheckedException {
    public InexistentUserException() {
        super("Specified user does not exist!");
    }
}
