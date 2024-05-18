package org.example.Exceptions.Unchecked;

public class InexistentPageException extends AudioLibraryUncheckedException {
    public InexistentPageException() {
        super("Inexistent page number. Try again");
    }
}
