package org.example;

import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;

public class App {

    private App() {}

    /**
     * Initializes the database and starts a session.
     * <p>
     * This method sets up the database tables using a {@link DBWrapper} and then starts a new session.
     * </p>
     */
    public static void RUN() {

        DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
        dbWrapper.createTables();

        Session.start();
    }
}
