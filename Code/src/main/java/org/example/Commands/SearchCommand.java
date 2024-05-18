package org.example.Commands;

import java.util.ArrayList;
import org.example.Data.Song;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.PageManager.PageManager;
import org.example.Utils.UserTypes;

public class SearchCommand extends AbstractCommand {
    public SearchCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
    }

    @Override
    public boolean execute() {
        DBWrapper<Song> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[] {getArgs()[0]};
        String[] forGet = new String[] {"name", "author", "year", "id"};
        ArrayList<Object> arrayList =
                dbWrapper.selectSearch(new Song(getArgs()[1], getArgs()[1], 0), forCheck, forGet);
        PageManager pageManager = new PageManager(arrayList, 4);
        pageManager.createPages();
        setSuccessMessage(
                pageManager.showCommandResult(
                        Integer.parseInt(getArgs()[2]),
                        "search " + getArgs()[0] + " \"" + getArgs()[1] + "\""));
        return true;
    }
}
