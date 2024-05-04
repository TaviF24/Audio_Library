package org.example.Commands;

import org.example.Utils.UserTypes;

public abstract class AbstractCommand implements Command {

    private UserTypes userType;

    public void setAllowedTypeUser(UserTypes newUserType){
        userType = newUserType;
    }

    public UserTypes getAllowedTypeUser(){
        return userType;
    }

}
