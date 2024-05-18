package org.example.Data;

import java.util.HashMap;

public class PlaylistSongs extends AbstractTableClassMapper {

    private final int idPlaylist;
    private final int idSong;

    public PlaylistSongs(int idPlaylist, int idSong) {
        this.idPlaylist = idPlaylist;
        this.idSong = idSong;
        setCorrespondingTable("PlaylistSongs");
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("idPlaylist", idPlaylist);
        hashMap.put("idSong", idSong);
        return hashMap;
    }
}
