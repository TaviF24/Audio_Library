package org.example.Exceptions.Checked;

public class InvalidPathException extends AudioLibraryCheckedException {
    public InvalidPathException(String message) {
        super("Unknown file " + message + ".");
    }
}
