package org.example.Users;

import org.example.Commands.AbstractCommand;
import org.example.Utils.UserTypes;

public abstract class User {

    private int Id;
    private String username;
    private String password;
    private UserTypes userTypes;

    public void setUserTypes(UserTypes userTypes) {
        this.userTypes = userTypes;
    }

    public UserTypes getUserTypes() {
        return userTypes;
    }

}
