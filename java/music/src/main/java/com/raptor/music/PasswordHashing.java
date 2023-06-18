package com.raptor.music;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordHashing {
    private static final int SALT_LENGTH = 16; // in bytes

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        String username = "myuser";
        String password = "mypassword";
        insertUser(username, password);
    }

    public static void insertUser(String username, String password) throws NoSuchAlgorithmException, SQLException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        // Hash the password with the salt
        String hashedPassword = hashPassword(password, salt);

        // Insert the user into the database
        String url = "jdbc:mysql://localhost:3306/users";
        String user = "Tyler";
        String sqlpassword = "Blackrobin7";
        try (Connection conn = DriverManager.getConnection(url, user, sqlpassword)) {
            String sql = "INSERT INTO users (username, password_hash, salt) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setBytes(2, hexToBytes(hashedPassword));
                stmt.setBytes(3, salt);
                stmt.executeUpdate();
            }
        }
    }
private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
    // Concatenate the password and salt
    byte[] passwordBytes = password.getBytes();
    byte[] combined = new byte[passwordBytes.length + salt.length];
    System.arraycopy(passwordBytes, 0, combined, 0, passwordBytes.length);
    System.arraycopy(salt, 0, combined, passwordBytes.length, salt.length);

    // Hash the concatenated value using SHA256
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(combined);

    // Encode the salt and hash values as hexadecimal strings
    String saltString = bytesToHex(salt);
    String hashString = bytesToHex(hash);

    // Concatenate the salt and hash strings with a separator
    String concatenatedString = saltString + ":" + hashString;
    if (concatenatedString.length() % 2 != 0) {
        concatenatedString = "0" + concatenatedString;
    }
    return concatenatedString;
}

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private static byte[] hexToBytes(String hexString) throws IllegalArgumentException {
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Input string must have an even number of characters");
        }
        int len = hexString.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return result;
    }
}