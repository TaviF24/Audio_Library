package org.example.Exceptions.Unchecked;

public class UserAlreadyAdminException extends AudioLibraryUncheckedException {
    public UserAlreadyAdminException() {
        super("User is already admin");
    }
}
