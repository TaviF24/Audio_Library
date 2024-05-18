package org.example.DatabaseManager;

public class Credits {
    private Credits(){}

    /**
     * Retrieves the connection credentials for the database.
     * <p>
     * This method creates and returns a new instance of {@link ConnectionCredits}
     * with the specified path, username, and password.
     * </p>
     *
     * @return a {@link ConnectionCredits} object containing the database connection credentials.
     */
    public static ConnectionCredits getConnectionCredits(){
        return new ConnectionCredits("jdbc:mysql://localhost:3306/AudioLibrary", "root", "parola123");
    }
}
