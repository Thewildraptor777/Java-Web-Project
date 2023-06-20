package com.raptor.music.user;

import java.security.SecureRandom;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    
    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        
        // Generate random characters from PASSWORD_CHARS
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(PASSWORD_CHARS.length());
            sb.append(PASSWORD_CHARS.charAt(randomIndex));
        }
        
        return sb.toString();
    }
    
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12); // Generate a salt with 12 rounds of hashing
        String hashedPassword = BCrypt.hashpw(password, salt); // Hash the password with the salt
        return hashedPassword;
    }
    
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword); // Check if the password matches the hashed password
    }
    
    public static void main(String[] args) {
        String password = "test"; // Generate a 12-character password
        String hashedPassword = hashPassword(password); // Hash the password
        
        // In a real application, you would store the hashed password in a secure manner, such as in a database
        System.out.println("Generated password: " + password);
        System.out.println("Hashed password: " + hashedPassword);
        
        // Test the password checking function
        String enteredPassword = "test";
        if (checkPassword(enteredPassword, hashedPassword)) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }
    }
}