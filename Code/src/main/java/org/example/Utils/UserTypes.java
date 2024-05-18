package org.example.Utils;

public enum UserTypes {
    ANONYMOUS,
    AUTHENTICATED,
    ADMIN;

    /**
     * Maps a string representation of user type to the corresponding UserTypes enum value.
     *
     * @param userType the string representation of the user type
     * @return the UserTypes enum value corresponding to the provided string
     * @throws ClassNotFoundException if an invalid user type string is provided
     */
    public static UserTypes mapper(String userType) throws ClassNotFoundException {
        switch (userType) {
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
