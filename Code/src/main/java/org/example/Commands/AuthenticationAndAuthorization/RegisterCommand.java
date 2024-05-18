package org.example.Commands.AuthenticationAndAuthorization;

import org.example.Commands.AbstractCommand;
import org.example.Data.Users.UserFactory;
import org.example.DatabaseManager.Credits;
import org.example.Data.Users.User;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Unchecked.DataAlreadyAddedException;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class RegisterCommand extends AbstractCommand {

    public RegisterCommand(String[] args){
        super(args);
        addAllowedTypeUser(UserTypes.ANONYMOUS);
        setSuccessMessage("Registered account with user name: " + args[0] + "\nYou are now authenticated as " + args[0]);
    }

    @Override
    public boolean execute() {
        try{
            DBWrapper<User> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
            User user = UserFactory.create(UserTypes.AUTHENTICATED, getArgs());

            String[] forCheck = new String[]{"username"};
            String[] forGet = new String[]{"id"};
            ArrayList<Object> results = dbWrapper.selectCheckIfExists(user, forCheck, forGet);
            if (!results.isEmpty()){
                throw new DataAlreadyAddedException("User with given username already exists! Please try again!");
            }
            if(dbWrapper.getSizeOfTable(user) == 0)
                user = UserFactory.create(UserTypes.ADMIN, getArgs());
            dbWrapper.insertIntoDB(user);
            Session.setSessionUser(user);
            return true;
        }catch (ClassNotFoundException e){
            System.err.println("System error\n" + e);
            return false;
        }
    }
}
