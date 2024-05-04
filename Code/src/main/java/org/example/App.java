package org.example;

import org.example.CommandInterface.CommandInterface;
import org.example.Commands.Invoker;
import org.example.Commands.LoginCommand;
import org.example.DatabaseManager.DBWrapper;
import org.example.Users.AnonymousUser;
import org.example.Users.User;

public class App {

    private App() {}

    public static void RUN() {

        DBWrapper dbWrapper = new DBWrapper();
        dbWrapper.createTables("jdbc:mysql://localhost:3306/AudioLibrary", "root", "parola123");

        User user = new AnonymousUser();

        CommandInterface commandInterface = CommandInterface.getINSTANCE();
        commandInterface.listenForCommands(user);

    }
}
