package org.example.Data;

import java.util.HashMap;

public class CommandForTable extends AbstractTableClassMapper {

    private int id;
    private final int idUser;
    private final String command;
    private final Boolean success;

    public CommandForTable(String command, int idUser, Boolean success) {
        this.command = command;
        this.idUser = idUser;
        this.success = success;
        setCorrespondingTable("Commands");
    }

    public CommandForTable(int id, int idUser, String command, Boolean success) {
        this.id = id;
        this.idUser = idUser;
        this.command = command;
        this.success = success;
        setCorrespondingTable("Commands");
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("idUser", idUser);
        hashMap.put("command", command);
        hashMap.put("success", success);

        return hashMap;
    }
}
