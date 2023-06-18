package com.raptor.music;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class password {
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
       boolean test= checkPassword("myuser", "mypassword");
    System.out.println(test);
    }
    public static boolean checkPassword(String username, String password) throws NoSuchAlgorithmException, SQLException {
    // Retrieve the user's salt and hashed password from the database
    String url = "jdbc:mysql://localhost:3306/users";
    String user = "Tyler";
    String sqlpassword = "Blackrobin7";
    try (Connection conn = DriverManager.getConnection(url, user, sqlpassword)) {
        String sql = "SELECT salt, password_hash FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    byte[] salt = rs.getBytes("salt");
                    String hashedPassword = rs.getString("password_hash");

                    // Hash the input password with the retrieved salt
                    String inputHashedPassword = hashPassword(password, salt);

                    // Compare the hashed passwords
                    return hashedPassword.equals(inputHashedPassword);
                } else {
                    // No user with the given username was found
                    return false;
                }
            }
        }
    }
}

    private static String hashPassword(String password, byte[] salt) {
        return null;
    }
}
