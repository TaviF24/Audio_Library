package org.example.Commands;

import org.example.AbstractTableClassMapper;
import org.example.Data.Playlist;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.DataAlreadyAddedException;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.util.ArrayList;

public class CreatePlaylistCommand extends AbstractCommand{
    public CreatePlaylistCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Playlist " + args[0] + " was created successfully!");
    }

    @Override
    public boolean execute() {
        DBWrapper<AbstractTableClassMapper> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[]{"username"};
        String[] forGet = new String[]{"id"};
        int idUser = (int) dbWrapper.selectCheckIfExists(Session.getSessionUser(), forCheck, forGet).get(0);

        forCheck = new String[]{"idUser", "name"};
        forGet = new String[]{"id"};
        Playlist playlist = new Playlist(idUser , getArgs()[0]);
        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
        if(!arrayList.isEmpty()){
            throw new DataAlreadyAddedException("You already have a playlist named " + getArgs()[0]);
//            throw new PlaylistAlreadyAddedException(getArgs()[0]);
        }
        dbWrapper.insertIntoDB(playlist);
        return true;
    }
}
