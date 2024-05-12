package org.example.Commands.AuthenticationAndAuthorization;

import org.example.Commands.AbstractCommand;
import org.example.Data.Users.AdminUser;
import org.example.Data.Users.AuthenticatedUser;
import org.example.Data.Users.User;
import org.example.Data.Users.UserFactory;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.InvalidLoginException;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class LoginCommand extends AbstractCommand {

    public LoginCommand(String[] args){
        super(args);
        addAllowedTypeUser(UserTypes.ANONYMOUS);
        setSuccessMessage("You are now authenticated as " + args[0]);
    }
    @Override
    public boolean execute() {
        try{
            DBWrapper<User> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
            User user =  UserFactory.create(UserTypes.AUTHENTICATED, getArgs());

            String[] forCheck = new String[]{"username", "password"};
            String[] forGet = new String[]{"type"};
            ArrayList<Object> results = dbWrapper.selectCheckIfExists(user, forCheck, forGet);
            if (results.isEmpty()){
                throw new InvalidLoginException();
    //            return false;
            }
            if(!results.get(0).equals(user.getUserTypes().toString())){
                user = UserFactory.create(UserTypes.mapper(results.get(0).toString()), getArgs());
            }
            Session.setSessionUser(user);
            return true;
        }catch (ClassNotFoundException e){
            System.err.println("System error\n" + e);
            return false;
        }
    }
}
