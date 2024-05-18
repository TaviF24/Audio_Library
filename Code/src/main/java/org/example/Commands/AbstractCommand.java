package org.example.Commands;

import org.example.Utils.UserTypes;

import java.util.ArrayList;

public abstract class AbstractCommand implements Command {

    private final ArrayList<UserTypes> userType;
    private final String[] args;
    private String successMessage;

    public AbstractCommand(String[] args) {
        this.args = args;
        userType = new ArrayList<>();
    }

    public void addAllowedTypeUser(UserTypes newUserType){
        userType.add(newUserType);
    }

    public ArrayList<UserTypes> getAllowedTypeUser(){
        return userType;
    }

    public String[] getArgs() {
        return args;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
