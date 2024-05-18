package org.example.Commands;

import org.example.Data.Song;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Unchecked.DataAlreadyAddedException;
import org.example.Utils.UserTypes;

public class CreateSongCommand extends AbstractCommand {
    public CreateSongCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ADMIN);
        setSuccessMessage("Added " + args[0] + " by " + args[1] + " to the library.");
    }

    @Override
    public boolean execute() {
        DBWrapper<Song> dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
        String[] forCheck = new String[] {"name", "author"};
        String[] forGet = new String[] {"id"};
        Song song = new Song(getArgs()[0], getArgs()[1], Integer.parseInt(getArgs()[2]));
        if (!dbWrapper.selectCheckIfExists(song, forCheck, forGet).isEmpty()) {
            throw new DataAlreadyAddedException("This song is already part of the library!");
        }
        dbWrapper.insertIntoDB(song);
        return true;
    }
}
