package org.example.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractTableClassMapper implements TableClassesMapper{
    private String correspondingTable;

    @JsonIgnore
    public String getCorrespondingTable() {
        return correspondingTable;
    }

    public void setCorrespondingTable(String correspondingTable) {
        this.correspondingTable = correspondingTable;
    }
}
