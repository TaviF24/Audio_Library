package org.example.DatabaseManager;

import org.example.AbstractTableClassMapper;
import org.example.Data.CommandForTable;
import org.example.Data.Users.User;

import java.sql.*;
import java.util.*;

public class DBWrapper<T extends AbstractTableClassMapper> {

    private final String path;
    private final String user;
    private final String password;

    public DBWrapper(ConnectionCredits connectionCredits) {
        this.path = connectionCredits.path();
        this.user = connectionCredits.user();
        this.password = connectionCredits.password();
    }

    public void createTables() {
        try (Connection connection = DriverManager.getConnection(path, user, password);
                Statement statement = connection.createStatement()) {

            String createTableUsers =
                    """
                    CREATE TABLE IF NOT EXISTS Users (
                                                id INT auto_increment,
                                                username VARCHAR(254) NOT NULL,
                                                password VARCHAR(254) NOT NULL,
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
                                                year INT NOT NULL,
                                                PRIMARY KEY (id),
                                                CONSTRAINT UK_SongsAuthor UNIQUE KEY (name, author)
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
            System.err.println("DBWrapper error\n" + e);
        }
    }

    public void insertIntoDB(T object){

        HashMap<String, Object> hashMap = object.getColumns();
        Set<String> set = hashMap.keySet();
        String tableName = object.getCorrespondingTable();
        StringBuilder questionMarks = new StringBuilder("(");
        for(int i=0; i<set.size()-1; i++){
            questionMarks.append("?, ");
        }
        questionMarks.append("?);");

        StringBuilder columns = new StringBuilder("(");
        for (String s : set) {
            columns.append(s).append(", ");
        }
        columns.replace(columns.length()-2,columns.length(),")");

        String insertIntoDB = "INSERT INTO " + tableName + columns + " VALUES " + questionMarks;

        try(
                Connection connection = DriverManager.getConnection(path, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(insertIntoDB)
        ){
            int index = 0;
            for (String s : set) {
                Object o = hashMap.get(s);
                switch (o.getClass().getSimpleName()){
                    case "String":
                        preparedStatement.setString(index+1, (String) o);
                        break;
                    case "Integer":
                        preparedStatement.setInt(index+1, (Integer) o);
                        break;
                    case "Boolean":
                        preparedStatement.setBoolean(index+1, (Boolean) o);
                        break;
                }
                index++;
            }
            preparedStatement.execute();
        }catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
    }

    public ArrayList<Object> selectCheckIfExists(T object, String[] columnsToCheck, String[] columnsToGet){
        String table = object.getCorrespondingTable();
        StringBuilder selectString = new StringBuilder("SELECT ");

        if(columnsToGet.length > 0){
            selectString.append(columnsToGet[0]);
        }
        for(int i=1;i<columnsToGet.length;i++){
            selectString.append(", ").append(columnsToGet[i]);
        }

        selectString.append(" FROM ").append(table).append(" ");
        HashMap<String,Object> hashMap = object.getColumns();

        StringBuilder conditions = new StringBuilder();
        if(columnsToCheck.length>0){
            conditions.append("WHERE ").append(columnsToCheck[0]).append(" = ?");
        }
        for(int i=1;i<columnsToCheck.length;i++){
            conditions.append(" AND ").append(columnsToCheck[i]).append(" = ?");
        }
        selectString.append(conditions).append(";");

//        System.out.println(selectString);

        /*
        SELECT fields FROM table WHERE column = ?[, AND column = ?, ...];
        */

        ArrayList<Object> results = new ArrayList<>();

        try(
                Connection connection = DriverManager.getConnection(path, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectString.toString())
        ){
            for(int i=0;i<columnsToCheck.length;i++){
                Object o = hashMap.get(columnsToCheck[i]);
                switch (o.getClass().getSimpleName()){
                    case "String":
                        preparedStatement.setString(i+1, (String) o);
                        break;
                    case "Integer":
                        preparedStatement.setInt(i+1, (Integer) o);
                        break;
                    case "Boolean":
                        preparedStatement.setBoolean(i+1, (Boolean) o);
                        break;
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                for(int i=0;i<columnsToGet.length;i++){
                    Object o = hashMap.get(columnsToGet[i]);
                        switch (o.getClass().getSimpleName()){
                            case "String":
                                results.add(resultSet.getString(columnsToGet[i]));
                                break;
                            case "Integer":
                                results.add(resultSet.getInt(columnsToGet[i]));
                                break;
                            case "Boolean":
                                results.add(resultSet.getBoolean(columnsToGet[i]));
                                break;
                        }
                }
            }
        }catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
        return results;
    }

    public void saveCommand(User user, String command, Boolean flag){
        String selectFromDB = """
                SELECT id FROM Users WHERE username = ?;
                """;
        try(
                Connection connection = DriverManager.getConnection(path, this.user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectFromDB)
        ){
            int id;
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt("id");
                DBWrapper<CommandForTable>dbWrapper = new DBWrapper<>(Credits.getConnectionCredits());
                dbWrapper.insertIntoDB(new CommandForTable(command, id, flag));
            }
        }
        catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
    }

    public int getSizeOfTable(T object){
        String table = object.getCorrespondingTable();
        int size = 0;
        String selectCount = "SELECT COUNT(*) AS number FROM " + table +";";
        try(
                Connection connection = DriverManager.getConnection(path, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectCount)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                size = resultSet.getInt("number");
            }
        }catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
        return size;
    }

    public void updateIntoDB(T object, String[] columnsToUpdate, String[] columnsToCheck){
        String table = object.getCorrespondingTable();
        HashMap<String, Object> hashMap = object.getColumns();
        StringBuilder updateString = new StringBuilder("UPDATE " + table + " SET " + columnsToUpdate[0] + " = ?");
        for(int i=1; i<columnsToUpdate.length; i++){
            updateString.append(", ").append(columnsToUpdate[i]).append(" = ?");
        }
        if(columnsToCheck.length != 0){
            updateString.append(" WHERE ").append(columnsToCheck[0]).append(" = ?");
        }
        for(int i=1; i<columnsToCheck.length; i++){
            updateString.append(" AND ").append(columnsToCheck[i]).append(" = ?");
        }
        updateString.append(";");
        try(
                Connection connection = DriverManager.getConnection(path, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(updateString.toString())
        ) {
            for (int i = 0; i < columnsToUpdate.length; i++) {
                Object o = hashMap.get(columnsToUpdate[i]);
                switch (o.getClass().getSimpleName()) {
                    case "String":
                        preparedStatement.setString(i + 1, (String) o);
                        break;
                    case "Integer":
                        preparedStatement.setInt(i + 1, (Integer) o);
                        break;
                    case "Boolean":
                        preparedStatement.setBoolean(i + 1, (Boolean) o);
                        break;
                }
            }
            for(int i=0;i<columnsToCheck.length;i++){
                Object o = hashMap.get(columnsToCheck[i]);
                int index = columnsToUpdate.length + i + 1;
                switch (o.getClass().getSimpleName()){
                    case "String":
                        preparedStatement.setString(index, (String) o);
                        break;
                    case "Integer":
                        preparedStatement.setInt(index, (Integer) o);
                        break;
                    case "Boolean":
                        preparedStatement.setBoolean(index, (Boolean) o);
                        break;
                }
            }
            preparedStatement.execute();
        }catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
    }

    public ArrayList<Object> selectSearch(T object, String[] columnsToCheck, String[] columnsToGet){
        String table = object.getCorrespondingTable();
        HashMap<String, Object> hashMap = object.getColumns();
        StringBuilder selectString = new StringBuilder("SELECT ");

        if(columnsToGet.length > 0){
            selectString.append(columnsToGet[0]);
        }
        for(int i=1;i<columnsToGet.length;i++){
            selectString.append(", ").append(columnsToGet[i]);
        }

        selectString.append(" FROM ").append(table).append(" ");

        StringBuilder conditions = new StringBuilder();
        boolean flag = false;
        if(columnsToCheck.length>0 && hashMap.get(columnsToCheck[0]).getClass().getSimpleName().equals("String")){
            conditions.append("WHERE ").append(columnsToCheck[0]).append(" LIKE '%").append(hashMap.get(columnsToCheck[0])).append("%'");
            for(int i=1;i<columnsToCheck.length;i++){
                if(!hashMap.get(columnsToCheck[0]).getClass().getSimpleName().equals("String")){
                    flag = true;
                    break;
                }
                conditions.append(" AND ").append(columnsToCheck[0]).append(" LIKE '%").append(hashMap.get(columnsToCheck[0])).append("%'");
            }
        }
        if(!flag){
            selectString.append(conditions);
        }
        selectString.append(";");
        System.out.println(selectString);

        ArrayList<Object> results = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(path, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(selectString.toString())
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                for (String s : columnsToGet) {
                    results.add(resultSet.getString(s));
                }
            }
        }catch (SQLException e){
            System.err.println("DBWrapper error\n" + e);
        }
        return results;
    }
}
