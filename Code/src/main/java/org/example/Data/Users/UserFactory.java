package org.example.Data.Users;

import org.example.Utils.UserTypes;

public class UserFactory {
    public static User create(UserTypes userType, String[] credentials) throws ClassNotFoundException {
        switch (userType){
            case AUTHENTICATED -> {return new AuthenticatedUser(credentials[0], credentials[1]);}
            case ADMIN -> {return new AdminUser(credentials[0], credentials[1]);}
            case ANONYMOUS -> {return new AnonymousUser();}
            default -> {
                throw new ClassNotFoundException("Invalid type of user");
            }
        }
    }
}
