package org.example;

import org.example.DatabaseManager.DBWrapper;

public class Main {
    public static void main(String[] args) {
        System.out.println("A");
        System.out.println("S");

        DBWrapper dbWrapper = new DBWrapper();
        dbWrapper.createTables("jdbc:mysql://localhost:3306/AudioLibrary", "root", "parola123");
    }
}
