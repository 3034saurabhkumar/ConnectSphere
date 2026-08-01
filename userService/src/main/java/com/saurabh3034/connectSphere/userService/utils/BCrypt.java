package com.saurabh3034.connectSphere.userService.utils;

import static org.mindrot.jbcrypt.BCrypt.*;

public class BCrypt {

    public static String hashPassword(String password) {
        return hashpw(password, gensalt());
    }

    public static boolean match(String password, String hashedPassword) {
        return checkpw(password, hashedPassword);
    }
}
