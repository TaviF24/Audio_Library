package org.example.Data.Users;

import org.example.Utils.UserTypes;

public class AnonymousUser extends User{
    public AnonymousUser() {
        super();
        setUserTypes(UserTypes.ANONYMOUS);
    }
}
