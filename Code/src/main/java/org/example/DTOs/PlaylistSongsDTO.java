package org.example.DTOs;

import org.example.Data.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaylistSongsDTO extends AbstractDTO{
    private String playlistName = "";
    private List<Song> songs = new ArrayList<>();

    public PlaylistSongsDTO(String playlistName, List<Song> songs) {
        this.playlistName = playlistName;
        this.songs = songs;
    }

    public PlaylistSongsDTO() {} //need for import

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public List<Object> getListToBeExported() {
        return new ArrayList<>(songs);
    }

    @Override
    public void setList(List<Object> list) {
        songs = new ArrayList<>();
        for (Object o : list) {
            songs.add((Song) o);
        }
    }

    @Override
    public HashMap<String, Object> getAllFields() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("playlistName", playlistName);
        hashMap.put("songs", songs);
        return hashMap;
    }

    @Override
    public Class getTypeOfElementsOfList() {
        return Song.class;
    }

    @Override
    public void setFieldsFromList(List<String> list) {
        HashMap<String, Object> hashMap = getAllFields();
        ArrayList<Song> arrayList = new ArrayList<>();
        int i=0;
        while(i<list.size()){
            String[] currentLine = list.get(i).split(" -> ");
            if(hashMap.containsKey(currentLine[0])){
                if("songs".equals(currentLine[0])){
                    int nrOfSongs = Integer.parseInt(list.get(i+1));
                    i++;
                    int rest = list.size()-i-1;
                    if(rest < nrOfSongs*3){  //nr of fields
                        throw new IndexOutOfBoundsException("Invalid input");
                    }
                    for(int j=0; j<nrOfSongs; j++){
                        String name = "";
                        String author = "";
                        int year = 0;
                        for(int k=0; k<3; k++){
                            String line = list.get(i+1);
                            i++;
                            String[] fieldValue = line.split(" -> ");
                            switch (fieldValue[0]){
                                case "name":{
                                    name = fieldValue[1];
                                    break;
                                }
                                case "author":{
                                    author = fieldValue[1];
                                    break;
                                }
                                case "year": {
                                    year = Integer.parseInt(fieldValue[1]);
                                    break;
                                }
                            }
                        }
                        arrayList.add(new Song(name, author, year));
                    }
                    i++;
                }else{
                    playlistName = currentLine[1];
                    i++;
                }
            }else{
                i++;
            }
        }
        songs = arrayList;
    }
}
