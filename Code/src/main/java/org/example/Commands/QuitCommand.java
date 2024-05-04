package org.example.Commands;

import org.example.Utils.UserTypes;

public class QuitCommand extends AbstractCommand{

    public QuitCommand(){
        setAllowedTypeUser(UserTypes.ANONYMOUS);
    }
    @Override
    public void execute() {
        System.out.println("Quitting");
    }
}
