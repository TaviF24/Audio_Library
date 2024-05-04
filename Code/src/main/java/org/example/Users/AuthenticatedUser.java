package org.example.Users;

import org.example.Utils.UserTypes;

public class AuthenticatedUser extends User{
    public AuthenticatedUser() {
        setUserTypes(UserTypes.AUTHENTICATED);
    }
}
