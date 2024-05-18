package org.example.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class Song extends AbstractTableClassMapper {

    private int id;
    private String name;
    private String author;
    private int year;

    public Song(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        setCorrespondingTable("Songs");
    }

    public Song() {     // need it for import
        setCorrespondingTable("Songs");
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Song(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        setCorrespondingTable("Songs");
    }

    @JsonIgnore
    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("author", author);
        hashMap.put("year", year);
        hashMap.put("id", id);
        return hashMap;
    }

    @Override
    public String toString() {
        return "name -> " + name + "\nauthor -> " + author + "\nyear -> " + year;
    }
}
