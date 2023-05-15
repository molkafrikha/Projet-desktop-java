/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.services;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author T480s
 */
public class PasswordHash {

    private static final int BCRYPT_ROUNDS = 12;

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(BCRYPT_ROUNDS);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
