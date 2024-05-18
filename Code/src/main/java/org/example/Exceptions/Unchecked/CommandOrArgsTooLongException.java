package org.example.Exceptions.Unchecked;

public class CommandOrArgsTooLongException extends AudioLibraryUncheckedException {
    public CommandOrArgsTooLongException() {
        super("The given input is too long. Check the size of command and arguments and try again");
    }
}
