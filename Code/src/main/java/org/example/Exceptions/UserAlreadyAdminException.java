package org.example.Exceptions;

public class UserAlreadyAdminException extends AudioLibraryRuntimeException{
    public UserAlreadyAdminException() {
        super("User is already admin");
    }
}
