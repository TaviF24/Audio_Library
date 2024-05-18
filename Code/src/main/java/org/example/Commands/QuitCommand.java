package org.example.Commands;

import org.example.Utils.UserTypes;

public class QuitCommand extends AbstractCommand {

    public QuitCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ANONYMOUS);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Quitting");
    }

    @Override
    public boolean execute() {
        return true;
    }
}
