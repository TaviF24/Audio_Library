package org.example.Exceptions.Unchecked;

public class InexistentPlaylistException extends AudioLibraryUncheckedException {
    public InexistentPlaylistException(String identifier) {
        super("Playlist " + identifier + " does not exist!");
    }

    public InexistentPlaylistException() {
        super("The desired playlist does not exist.");
    }
}
