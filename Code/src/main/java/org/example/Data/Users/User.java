package org.example.Data.Users;

import java.util.HashMap;
import org.example.Data.AbstractTableClassMapper;
import org.example.Utils.UserTypes;

public abstract class User extends AbstractTableClassMapper {

    private int id;
    private String username;
    private String password;
    private UserTypes userTypes;

    public User() {
        setCorrespondingTable("Users");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setCorrespondingTable("Users");
    }

    public String getUsername() {
        return username;
    }

    public void setUserTypes(UserTypes userTypes) {
        this.userTypes = userTypes;
    }

    public UserTypes getUserTypes() {
        return userTypes;
    }

    @Override
    public HashMap<String, Object> getColumns() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        hashMap.put("type", userTypes.toString());
        hashMap.put("id", id);

        return hashMap;
    }
}
