package org.example.Commands;

import org.example.Data.AbstractTableClassMapper;
import org.example.DTOStorage.Storage;
import org.example.DTOs.PlaylistSongsDTO;
import org.example.Data.Playlist;
import org.example.Data.PlaylistSongs;
import org.example.Data.Song;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Exceptions.Unchecked.InexistentPlaylistException;
import org.example.Session;
import org.example.Utils.UserTypes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExportPlaylistCommand extends AbstractCommand{
    public ExportPlaylistCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Successfully exported");
    }

    @Override
    public boolean execute() throws AudioLibraryCheckedException {
        DBWrapper<AbstractTableClassMapper> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[]{"username"};
        String[] forGet = new String[]{"id"};

        int userId = (int) dbWrapper.selectCheckIfExists(Session.getSessionUser(), forCheck, forGet).get(0);
        Playlist playlist;
        forGet = new String[]{"id", "name"};
        ArrayList<Object> arrayList = null;
        boolean found = false;
        try{
            int playlistId = Integer.parseInt(getArgs()[0]);
            forCheck = new String[]{"id", "idUser"};
            playlist = new Playlist(playlistId, userId, "");
            arrayList = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
            if(!arrayList.isEmpty()){
                found = true;
            }
        }catch (NumberFormatException ignored){}

        if(!found){
            forCheck = new String[]{"name", "idUser"};
            playlist = new Playlist(userId, getArgs()[0]);
            arrayList = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
            if(!arrayList.isEmpty()){
                found = true;
            }
        }
        if(!found){
            throw new InexistentPlaylistException(getArgs()[0]);
        }
        int playlistId = (int) arrayList.get(0);
        String playlistName = (String) arrayList.get(1);

        forGet = new String[]{"idSong"};
        forCheck = new String[]{"idPlaylist"};
        PlaylistSongs playlistSongs = new PlaylistSongs(playlistId, 0);
        ArrayList<Object> songIds = dbWrapper.selectCheckIfExists(playlistSongs, forCheck, forGet);

        forGet = new String[]{"name", "author", "year"};
        forCheck = new String[]{"id"};
        ArrayList<Song> songs = new ArrayList<>();
        for (Object id : songIds) {
            ArrayList<Object> songFields = dbWrapper.selectCheckIfExists(new Song((int)id, "", "", 0), forCheck, forGet);
            songs.add(new Song((String) songFields.get(0), (String) songFields.get(1), (Integer) songFields.get(2)));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String fileName = "src/main/java/org/example/ExportOutput/export_" +
                Session.getSessionUser().getUsername() + "_" + playlistName + "_" + simpleDateFormat.format(new Date());

//        System.out.println(fileName);

        ArrayList<PlaylistSongsDTO> playlistSongsDTOS = new ArrayList<>();
        playlistSongsDTOS.add(new PlaylistSongsDTO(playlistName, songs));

        Storage<PlaylistSongsDTO> storage = new Storage<>();
        try {
            storage.writeAll(playlistSongsDTOS, getArgs()[1], fileName);
        } catch (IOException e) {
            System.out.println("System error\n" + e);
            return false;
        }

        return true;
    }
}
