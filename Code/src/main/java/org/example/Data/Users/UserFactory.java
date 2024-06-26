package org.example.Data.Users;

import org.example.Utils.UserTypes;

public class UserFactory {

    /**
     * Creates a user object based on the provided user type and credentials.
     *
     * @param userType the type of user to create
     * @param credentials an array containing user credentials
     * @return a user object corresponding to the specified user type
     * @throws ClassNotFoundException if an invalid type of user is provided
     */
    public static User create(UserTypes userType, String[] credentials)
            throws ClassNotFoundException {
        switch (userType) {
            case AUTHENTICATED -> {
                return new AuthenticatedUser(credentials[0], credentials[1]);
            }
            case ADMIN -> {
                return new AdminUser(credentials[0], credentials[1]);
            }
            case ANONYMOUS -> {
                return new AnonymousUser();
            }
            default -> throw new ClassNotFoundException("Invalid type of user");
        }
    }
}
