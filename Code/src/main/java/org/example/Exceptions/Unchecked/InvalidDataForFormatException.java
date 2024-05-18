package org.example.Exceptions.Unchecked;

public class InvalidDataForFormatException extends AudioLibraryUncheckedException {
    public InvalidDataForFormatException() {
        super("This type of data is not accepted for this format");
    }
}
