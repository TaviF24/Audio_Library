package org.example.Commands.AuthenticationAndAuthorization;

import org.example.Commands.AbstractCommand;
import org.example.Data.Users.UserFactory;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Session;
import org.example.Utils.UserTypes;

public class LogoutCommand extends AbstractCommand {

    public LogoutCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Successfully logged out");
    }

    @Override
    public boolean execute() {
        try {
            DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
            dbWrapper.saveCommand(Session.getSessionUser(), "logout", true);
            Session.setSessionUser(UserFactory.create(UserTypes.ANONYMOUS, new String[0]));
        }catch (ClassNotFoundException e){
            System.err.println("System error\n" + e);
            return false;
        }
        return true;
    }
}
