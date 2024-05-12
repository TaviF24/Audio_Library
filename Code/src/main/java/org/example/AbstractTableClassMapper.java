package org.example;

public abstract class AbstractTableClassMapper implements TableClassesMapper{
    private String correspondingTable;

    public String getCorrespondingTable() {
        return correspondingTable;
    }

    public void setCorrespondingTable(String correspondingTable) {
        this.correspondingTable = correspondingTable;
    }
}
