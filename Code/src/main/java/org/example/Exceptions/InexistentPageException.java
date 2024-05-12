package org.example.Exceptions;

public class InexistentPageException extends AudioLibraryRuntimeException{
    public InexistentPageException() {
        super("Inexistent page number. Try again");
    }
}
