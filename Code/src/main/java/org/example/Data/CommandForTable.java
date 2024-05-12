package org.example.Data;

import org.example.AbstractTableClassMapper;
import org.example.Commands.AbstractCommand;

import java.util.HashMap;

public class CommandForTable extends AbstractTableClassMapper {
    private final int idUser;
    private final String command;
    private final Boolean success;

    public CommandForTable(String command, int idUser, Boolean success) {
        this.command = command;
        this.idUser = idUser;
        this.success = success;
        setCorrespondingTable("Commands");
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object>hashMap = new HashMap<>();
        hashMap.put("idUser", idUser);
        hashMap.put("command", command);
        hashMap.put("success", success);

        return hashMap;
    }
}
