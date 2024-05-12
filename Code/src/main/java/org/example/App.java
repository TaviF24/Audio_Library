package org.example;

import org.example.CommandInterface.CommandInterface;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Data.Users.AnonymousUser;
import org.example.Data.Users.User;

import java.io.*;

public class App {

    private App() {}

    public static void RUN() {

        DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
        dbWrapper.createTables();

        Session.start();
    }
}
