package org.example.Commands;

import org.example.AbstractTableClassMapper;
import org.example.Data.CommandForTable;
import org.example.Data.Users.User;
import org.example.Data.Users.UserFactory;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.InexistentUserException;
import org.example.PageManager.PageManager;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class AuditCommand extends AbstractCommand{
    public AuditCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ADMIN);
    }

    @Override
    public boolean execute() {
        DBWrapper<AbstractTableClassMapper> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[]{"username"};
        String[] forGet = new String[]{"id"};
        User user;
        try{
            user = UserFactory.create(UserTypes.AUTHENTICATED, new String[]{getArgs()[0],""});
        }catch (ClassNotFoundException e){
            System.err.println("System error\n" + e);
            return false;
        }
        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(user, forCheck, forGet);
        if(arrayList.isEmpty()){
            throw new InexistentUserException();
        }
        CommandForTable command = new CommandForTable("audit", (Integer) arrayList.get(0), true);
        forCheck[0] = "idUser";
        forGet = new String[]{"command", "success"};
        arrayList = dbWrapper.selectCheckIfExists(command, forCheck, forGet);

        PageManager pageManager = new PageManager(arrayList,  2);
        pageManager.createPages();
        setSuccessMessage(pageManager.showCommandResult(Integer.parseInt(getArgs()[1]), "audit " + getArgs()[0]));
        return true;
    }
}
