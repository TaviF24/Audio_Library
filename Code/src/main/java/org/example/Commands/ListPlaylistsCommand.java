package org.example.Commands;

import org.example.Data.AbstractTableClassMapper;
import org.example.Data.Playlist;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.PageManager.PageManager;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class ListPlaylistsCommand extends AbstractCommand{
    public ListPlaylistsCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
    }
    @Override
    public boolean execute() {

        DBWrapper<AbstractTableClassMapper> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[]{"username"};
        String[] forGet = new String[]{"id"};

        int userId = (int) dbWrapper.selectCheckIfExists(Session.getSessionUser(), forCheck, forGet).get(0);
        forCheck = new String[]{"idUser"};
        forGet = new String[]{"name", "id"};
        Playlist playlist = new Playlist(userId , "");
        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
        PageManager pageManager = new PageManager(arrayList,  2);
        pageManager.createPages();

        setSuccessMessage(pageManager.showCommandResult(Integer.parseInt(getArgs()[0]), "list playlists"));
//        System.out.println(arrayList);
        return true;
    }
}
