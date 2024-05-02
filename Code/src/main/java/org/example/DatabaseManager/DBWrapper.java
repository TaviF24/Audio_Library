package org.example.DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWrapper {
    public void createTables(String path, String user, String password) {
        try (Connection connection = DriverManager.getConnection(path, user, password);
                Statement statement = connection.createStatement()) {

            String createTableUsers =
                    """
                    CREATE TABLE IF NOT EXISTS Users (
                                                id INT auto_increment,
                                                username VARCHAR(254) NOT NULL,
                                                name VARCHAR(254) NOT NULL,
                                                type VARCHAR(254) NOT NULL,
                                                PRIMARY KEY (id),
                                                UNIQUE (username)
                                            );
                    """;

            String createTableCommands =
                    """
CREATE TABLE IF NOT EXISTS Commands (
                            id INT auto_increment,
                            idUser INT NOT NULL,
                            command VARCHAR(254) NOT NULL,
                            success BOOLEAN NOT NULL,
                            PRIMARY KEY (id),
                            CONSTRAINT FK_UserCommand FOREIGN KEY (idUser) REFERENCES Users(id)
                            ON DELETE CASCADE
                        );
""";

            String createTablePlaylists =
                    """
CREATE TABLE IF NOT EXISTS Playlists (
                            id INT auto_increment,
                            idUser INT NOT NULL,
                            name VARCHAR(254) NOT NULL,
                            PRIMARY KEY (id),
                            CONSTRAINT UK_PlaylistUser UNIQUE KEY (idUser,name),
                            CONSTRAINT FK_UserPlaylist FOREIGN KEY (idUser) REFERENCES Users(id)
                            ON DELETE CASCADE
                        );
""";

            String createTableSongs =
                    """
                    CREATE TABLE IF NOT EXISTS Songs (
                                                id INT auto_increment,
                                                name VARCHAR(254) NOT NULL,
                                                author VARCHAR(254) NOT NULL,
                                                date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                PRIMARY KEY (id),
                                                CONSTRAINT UK_SongsAuthor UNIQUE KEY (name,author)
                                            );
                    """;

            String createTablePlaylistSongs =
                    """
CREATE TABLE IF NOT EXISTS PlaylistSongs (
                            idPlaylist INT,
                            idSong INT,
                            CONSTRAINT PK_PlaylistSongs PRIMARY KEY (idPlaylist, idSong),
                            CONSTRAINT FK_PlaylistSongsPlaylist FOREIGN KEY (idPlaylist) REFERENCES Playlists(id)
                            ON DELETE CASCADE,
                            CONSTRAINT FK_PlaylistSongsSong FOREIGN KEY (idSong) REFERENCES Songs(id)
                            ON DELETE CASCADE
                        );
""";

            statement.execute(createTableUsers);
            statement.execute(createTableCommands);
            statement.execute(createTablePlaylists);
            statement.execute(createTableSongs);
            statement.execute(createTablePlaylistSongs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
