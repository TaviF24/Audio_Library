package org.example.Commands;

import java.util.ArrayList;
import org.example.Data.AbstractTableClassMapper;
import org.example.Data.Playlist;
import org.example.Data.PlaylistSongs;
import org.example.Data.Song;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Unchecked.*;
import org.example.Session;
import org.example.Utils.UserTypes;

public class AddCommand extends AbstractCommand {
    public AddCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
    }

    @Override
    public boolean execute() {
        DBWrapper<AbstractTableClassMapper> dbWrapper =
                new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[] {"username"};
        String[] forGet = new String[] {"id"};

        int userId =
                (int)
                        dbWrapper
                                .selectCheckIfExists(Session.getSessionUser(), forCheck, forGet)
                                .get(0);

        Playlist playlist;
        forGet = new String[] {"id", "name"};
        if (getArgs()[0].equals("byName")) {
            forCheck = new String[] {"name", "idUser"};
            playlist = new Playlist(userId, getArgs()[1]);
        } else {
            forCheck = new String[] {"id", "idUser"};
            playlist = new Playlist(Integer.parseInt(getArgs()[1]), userId, "");
        }

        ArrayList<Object> arrayList = dbWrapper.selectCheckIfExists(playlist, forCheck, forGet);
        if (arrayList.isEmpty()) {
            throw new InexistentPlaylistException();
        }
        int playlistId = (int) arrayList.get(0);
        String playlistName = arrayList.get(1).toString();
        boolean existError = false;
        forCheck = new String[] {"id"};
        forGet = new String[] {"name", "author"};

        String[] forCheckBefore = new String[] {"idPlaylist", "idSong"};
        String[] forGetBefore = new String[] {"idPlaylist"};
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<PlaylistSongs> playlistWithSongs = new ArrayList<>();

        for (int i = 2; i < getArgs().length; i++) {
            try {
                Song song = new Song(Integer.parseInt(getArgs()[i]), "", "", 0);
                ArrayList<Object> list = dbWrapper.selectCheckIfExists(song, forCheck, forGet);
                if (list.isEmpty()) {
                    existError = true;
                    throw new InexistentSongException(Integer.parseInt(getArgs()[i]));
                }
                String songName = list.get(0).toString();
                String songAuthor = list.get(1).toString();
                PlaylistSongs playlistSongs =
                        new PlaylistSongs(playlistId, Integer.parseInt(getArgs()[i]));

                if (!dbWrapper
                        .selectCheckIfExists(playlistSongs, forCheckBefore, forGetBefore)
                        .isEmpty()) {
                    existError = true;
                    throw new DataAlreadyAddedException(
                            "The song \""
                                    + songName
                                    + "\" by \""
                                    + songAuthor
                                    + "\" is already part of \""
                                    + playlistName
                                    + "\"");
                }

                songs.add(new Song(songName, songAuthor, 0));
                playlistWithSongs.add(
                        new PlaylistSongs(playlistId, Integer.parseInt(getArgs()[i])));
            } catch (NumberFormatException e) {
                throw new InvalidTypeOfParameterException("add " + getArgs()[0]);
            } catch (AudioLibraryUncheckedException e) {
                System.out.println(e.getMessage());
            }
        }
        if (existError) {
            return false;
        }
        StringBuilder successMessage = new StringBuilder();
        for (int i = 0; i < getArgs().length - 2; i++) {
            dbWrapper.insertIntoDB(playlistWithSongs.get(i));
            successMessage
                    .append("Added \"")
                    .append(songs.get(i).getName())
                    .append("\" by ")
                    .append(songs.get(i).getAuthor())
                    .append(" to ")
                    .append(playlistName)
                    .append(".\n");
        }
        setSuccessMessage(successMessage.toString());
        return true;
    }
}
