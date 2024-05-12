package org.example.Exceptions;

public class NoPermissionException extends AudioLibraryRuntimeException{

    public NoPermissionException() {
        super("You do not have the permission to use this!");
    }
}
