package org.example.Data.Users;

import org.example.Utils.UserTypes;

public class AuthenticatedUser extends User {
    public AuthenticatedUser(String username, String password) {
        super(username, password);
        setUserTypes(UserTypes.AUTHENTICATED);
    }
}
