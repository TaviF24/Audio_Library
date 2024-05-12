package org.example.Data;

import org.example.AbstractTableClassMapper;

import java.util.HashMap;

public class Song extends AbstractTableClassMapper {

    private int id;
    private final String name;
    private final String author;
    private final int year;

    public Song(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        setCorrespondingTable("Songs");
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("author", author);
        hashMap.put("year", year);
        hashMap.put("id", id);
        return hashMap;
    }
}
