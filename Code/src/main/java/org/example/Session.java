package org.example;

import java.io.InputStreamReader;
import java.io.Reader;
import org.example.CommandInterface.CommandInterface;
import org.example.Data.Users.AnonymousUser;
import org.example.Data.Users.User;

public class Session {

    private static final Reader sourceInput =
            new InputStreamReader(
                    System.in); // can be changed to read from files too, but needs try-catch
    private static User sessionUser;

    public Session() {
        sessionUser = new AnonymousUser();
    }

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        Session.sessionUser = sessionUser;
    }

    /**
     * Starts a new session with an anonymous user and begins listening for commands.
     * <p>
     * This method initializes the session user as an anonymous user and starts the command interface to listen for commands.
     * </p>
     */
    public static void start() {
        Session.setSessionUser(new AnonymousUser());

        CommandInterface commandInterface = CommandInterface.getINSTANCE();
        commandInterface.listenForCommands(sourceInput);
    }
}
