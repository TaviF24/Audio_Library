package org.example.Data;

import java.util.HashMap;

public class Playlist extends AbstractTableClassMapper {

    private int id;
    private final int idUser;
    private final String name;

    public Playlist(int idUser, String name) {
        this.idUser = idUser;
        this.name = name;
        setCorrespondingTable("Playlists");
    }

    public Playlist(int id, int idUser, String name) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        setCorrespondingTable("Playlists");
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("idUser", idUser);
        hashMap.put("name", name);
        hashMap.put("id", id);
        return hashMap;
    }
}
