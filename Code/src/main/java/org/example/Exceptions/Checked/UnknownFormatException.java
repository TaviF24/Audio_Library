package org.example.Exceptions.Checked;

public class UnknownFormatException extends AudioLibraryCheckedException {
    public UnknownFormatException() {
        super("Unknown format");
    }
}
