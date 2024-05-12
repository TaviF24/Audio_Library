package org.example.DatabaseManager;

public class Credits {
    private Credits(){}

    public static ConnectionCredits getConnectionCredits(){
        return new ConnectionCredits("jdbc:mysql://localhost:3306/AudioLibrary", "root", "parola123");
    }
}
