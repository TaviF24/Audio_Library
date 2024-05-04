package org.example.Users;

import org.example.Utils.UserTypes;

public class AnonymousUser extends User{
    public AnonymousUser() {
        setUserTypes(UserTypes.ANONYMOUS);
    }
}
