package org.example.Exceptions.Unchecked;

public class InexistentSongException extends AudioLibraryUncheckedException {
    public InexistentSongException(int identifier) {
        super("Song with identifier " + identifier + " does not exist.");
    }

    public InexistentSongException(String nameAndAuthor) {
        super("Song " + nameAndAuthor + " does not exist in the library.");
    }
}
