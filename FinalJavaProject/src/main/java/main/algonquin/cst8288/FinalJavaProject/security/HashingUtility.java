package main.algonquin.cst8288.FinalJavaProject.security;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtility {

    /**
     * Hashes a password using BCrypt.
     * 
     * @param password the password to hash
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifies a provided password against its hashed version.
     * 
     * @param password the password to verify
     * @param hashedPassword the hashed password for comparison
     * @return true if the password matches its hash, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

