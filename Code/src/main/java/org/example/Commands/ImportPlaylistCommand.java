package org.example.Commands;

import java.util.ArrayList;
import java.util.List;
import org.example.DTOStorage.Storage;
import org.example.DTOs.PlaylistSongsDTO;
import org.example.Data.AbstractTableClassMapper;
import org.example.Data.Playlist;
import org.example.Data.PlaylistSongs;
import org.example.Data.Song;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Exceptions.Unchecked.DataAlreadyAddedException;
import org.example.Exceptions.Unchecked.InexistentSongException;
import org.example.Session;
import org.example.Utils.UserTypes;

public class ImportPlaylistCommand extends AbstractCommand {
    public ImportPlaylistCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Successfully imported");
    }

    @Override
    public boolean execute() throws AudioLibraryCheckedException {
        String path = getArgs()[0];
        String[] tokens = path.split("\\.");
        String extension = tokens[tokens.length - 1];

        Storage<PlaylistSongsDTO> storage = new Storage<>();
        PlaylistSongsDTO playlistSongsDTO =
                storage.readAll(path, extension, new PlaylistSongsDTO());

        if (playlistSongsDTO.getPlaylistName() == "") {
            tokens = path.split("/");
            String file = tokens[tokens.length - 1];
            String name = file.substring(0, file.lastIndexOf('.'));
            playlistSongsDTO.setPlaylistName(name);
        }

        DBWrapper<AbstractTableClassMapper> dbWrapper =
                new DBWrapper<>(Credits.getConnectionCredits());

        String[] forCheck = new String[] {"username"};
        String[] forGet = new String[] {"id"};
        int userId =
                (int)
                        dbWrapper
                                .selectCheckIfExists(Session.getSessionUser(), forCheck, forGet)
                                .get(0);

        String playlistName = playlistSongsDTO.getPlaylistName();
        Playlist playlist = new Playlist(userId, playlistName);
        forCheck = new String[] {"idUser", "name"};
        ArrayList<Object> res = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
        if (!res.isEmpty()) {
            throw new DataAlreadyAddedException("Playlist already exists. Please try again");
        }

        dbWrapper.insertIntoDB(playlist);
        int idPlaylist = (int) dbWrapper.selectCheckIfExists(playlist, forCheck, forGet).get(0);

        forCheck = new String[] {"name", "author"};
        List<Object> list = playlistSongsDTO.getListToBeExported();
        for (Object o : list) {
            if (dbWrapper.selectCheckIfExists((Song) o, forCheck, forGet).isEmpty()) {
                throw new InexistentSongException(
                        ((Song) o).getName() + " by " + ((Song) o).getAuthor());
            }
        }

        for (Object o : list) {
            int idSong = (int) dbWrapper.selectCheckIfExists((Song) o, forCheck, forGet).get(0);
            dbWrapper.insertIntoDB(new PlaylistSongs(idPlaylist, idSong));
        }

        return true;
    }
}
