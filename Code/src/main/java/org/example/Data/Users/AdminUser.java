package org.example.Data.Users;

import org.example.Utils.UserTypes;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password);
        setUserTypes(UserTypes.ADMIN);
    }
}
