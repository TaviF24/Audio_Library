package org.example.Utils;

public enum UserTypes {
    ANONYMOUS,
    AUTHENTICATED,
    ADMIN;

    public static UserTypes mapper(String userType) throws ClassNotFoundException {
        switch (userType){
            case "ANONYMOUS" -> {
                return ANONYMOUS;
            }
            case "AUTHENTICATED" -> {
                return AUTHENTICATED;
            }
            case "ADMIN" -> {
                return ADMIN;
            }
            default -> throw new ClassNotFoundException();
        }
    }
}
