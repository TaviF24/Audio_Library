package org.example.Exceptions.Unchecked;

public class NoPermissionException extends AudioLibraryUncheckedException {

    public NoPermissionException() {
        super("You do not have the permission to use this!");
    }
}
