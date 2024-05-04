package org.example.Commands;

import org.example.Utils.UserTypes;

public class LoginCommand extends AbstractCommand{

    public LoginCommand(){
        setAllowedTypeUser(UserTypes.ANONYMOUS);
    }
    @Override
    public void execute() {
        System.out.println("Login done");
    }
}
