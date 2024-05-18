package org.example.Commands;

import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Session;
import org.example.Utils.UserTypes;

public class HelpCommand extends AbstractCommand{

    private static final String helpForAnonymous = """
            |--- quit : Stop the app running;
            |--- register "username" "password" : Register a new user and be authenticated as him;
            |--- login "username" "password" : Login to your account.""";
    private static final String helpForAuthenticated = """
            |--- quit : Stop the app running;
            |--- logout : Logout from your account;
            |--- create playlist "playlistName" : Create a playlist named "playlistName" if it does not exist;
            |--- list playlists [pageNumber] : List all the playlists of the current user. Page number is by default 1;
            |--- add (byName/byId) ("playlistName"/playlistId) songId [song2Id song3Id ...] : Add a song or a list of songs
            |       by their ids in a playlist after his name or id. Command fails if at least one song fails to be added;
            |--- search (author/name) "searchCriteria" [pageNumber] : Search and print all the songs that have at least a
            |       match with the "searchCriteria". Page number is by default 1;
            |--- export playlist ("playlistName"/playlistId) (format) : Export the playlist into a file named
            |       "export_username_playlistName_date.extension". The accepted formats are: json, csv, personal;
            |--- import playlist "absolutPathOfFile" : Import a playlist of songs from a file that have one of these
            |       formats: json, csv, txt. For the .txt extension, the format is personal.""";
    private static final String helpForAdmin = helpForAuthenticated + """
            
            |--- promote "username" : Make user "username" an admin;
            |--- create song "songName" "authorName" year : Add a song to the app's audio library;
            |--- redo commandId : Reuse a command;""";

    public HelpCommand(String[] args) {
        super(args);
        addAllowedTypeUser(UserTypes.ANONYMOUS);
        addAllowedTypeUser(UserTypes.AUTHENTICATED);
        addAllowedTypeUser(UserTypes.ADMIN);
    }

    @Override
    public boolean execute() throws AudioLibraryCheckedException {
        UserTypes userType = Session.getSessionUser().getUserTypes();
        switch (userType){
            case ANONYMOUS -> setSuccessMessage(helpForAnonymous);
            case AUTHENTICATED -> setSuccessMessage(helpForAuthenticated);
            case ADMIN -> setSuccessMessage(helpForAdmin);
        }
        return true;
    }
}
