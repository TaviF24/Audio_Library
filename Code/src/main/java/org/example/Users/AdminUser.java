package org.example.Users;

import org.example.Utils.UserTypes;

public class AdminUser extends User{

    public AdminUser() {
        setUserTypes(UserTypes.ADMIN);
    }
}
