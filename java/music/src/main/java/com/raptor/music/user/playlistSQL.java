package com.raptor.music.user;

import java.sql.*;

public class playlistSQL {
    public static void main(String[] args) {
        System.out.println(connect("dev"));

    }

    public static String connect(String user) {
        String url = "jdbc:mysql://localhost:3306/users"; // replace "mydatabase" with your database name
        String username = "Tyler"; // replace with your MySQL username
        String password = "Blackrobin7"; // replace with your MySQL password
        String data = "";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM data WHERE username = ?"; // replace "users" with your table name and
                                                                    // "username" with your column name
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user); // replace with the username you want to retrieve from the column

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String list = rs.getString("playlists"); // replace with your column name
                data = list;
            } else {
                System.out.println("User not found!");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database: " + e.getMessage());
        }

        return data;
    }
}