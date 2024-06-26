package org.example.Commands;

import java.util.ArrayList;
import org.example.Utils.UserTypes;

public abstract class AbstractCommand implements Command {

    private final ArrayList<UserTypes> userType;
    private final String[] args;
    private String successMessage;

    public AbstractCommand(String[] args) {
        this.args = args;
        userType = new ArrayList<>();
    }

    /**
     * Adds a new user type to the allowed types for this command.
     *
     * @param newUserType the new user type to add
     */
    public void addAllowedTypeUser(UserTypes newUserType) {
        userType.add(newUserType);
    }

    public ArrayList<UserTypes> getAllowedTypeUser() {
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
