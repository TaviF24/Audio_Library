package org.example.Commands;

import org.example.Data.Users.User;
import org.example.Data.Users.UserFactory;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Unchecked.InexistentUserException;
import org.example.Exceptions.Unchecked.UserAlreadyAdminException;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class PromoteCommand extends AbstractCommand{
    public PromoteCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage(args[0] + " is now an administrator!");
    }
    @Override
    public boolean execute() {
        try{
        DBWrapper<User> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        User user =  UserFactory.create(UserTypes.AUTHENTICATED, getArgs());

        String[] forCheck = new String[]{"username"};
        String[] forGet = new String[]{"id", "type"};
        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(user, forCheck, forGet);
        if(arrayList.isEmpty()){
            throw new InexistentUserException();
        }
        if(arrayList.get(1).equals(UserTypes.ADMIN.toString())){
            throw new UserAlreadyAdminException();
        }
        user = UserFactory.create(UserTypes.ADMIN, getArgs());
        forCheck = new String[]{"username"};
        String[] forUpdate = new String[]{"type"};
        dbWrapper.updateIntoDB(user, forUpdate, forCheck);
        }catch (ClassNotFoundException e){
            System.err.println("System error\n" + e);
            return false;
        }
        return true;
    }
}
