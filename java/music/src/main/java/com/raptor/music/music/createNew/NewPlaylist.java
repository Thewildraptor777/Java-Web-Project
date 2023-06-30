package com.raptor.music.music.createNew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.raptor.music.user.UserSql;

@Controller
public class NewPlaylist {
    @GetMapping("/newplaylist")
    public String page() {
        return "newMusic/newplaylist.html";
    }

    @PostMapping("/playlistsubmit")
    public ResponseEntity<?> handlePostRequest(@RequestBody List<List<String>> finalArray) {
        System.out.println(finalArray);
        String playlist = finalArray.get(0).get(0);
        write(playlist);
        insert(finalArray, playlist);
        //
        String newlist = finalArray.get(0).get(0);
        System.out.println(newlist);

        System.out.println(finalArray.get(finalArray.size() - 1).get(0));
        writeNew(finalArray.get(finalArray.size() - 1).get(0), newlist);
        // do something with the array
        return ResponseEntity.ok("Success");
    }

    public static void write(String playlist) {
        String url = "jdbc:mysql://localhost:3306/music";
        String user = "Tyler";
        String password = "Blackrobin7";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE " + playlist + " ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "link VARCHAR(300),"
                    + "image VARCHAR(300),"
                    + "title VARCHAR(300),"
                    + "artist VARCHAR(300)"
                    + ");";

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(List<List<String>> finalArray, String playlist) {
        String url = "jdbc:mysql://localhost:3306/music";
        String user = "Tyler";
        String password = "Blackrobin7";

        for (int index = 0; index < finalArray.size(); index++) {

            String data = "";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement()) {
                for (int i = 0; i < finalArray.get(index).size(); i++) {
                    data = data + '\'' + finalArray.get(index).get(i) + '\'' + ',';
                }
                data = data.substring(0, data.length() - 1);
                System.out.println(data);
                String sql = "INSERT INTO " + playlist + " (link, image, title, artist) "
                        + "VALUES (" + data + ")";

                int rowsAffected = stmt.executeUpdate(sql);
                System.out.println(rowsAffected + " row(s) affected");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String update() {
        return UserSql.read()[2];

    }

    public static void main(String[] args) {
        writeNew("dev", "test");
    }

    public static void writeNew(String enteredUser, String enteredPlaylist) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/users";
            String user = "Tyler";
            String password = "Blackrobin7";
            Connection conn = DriverManager.getConnection(url, user, password);

            // Retrieve the playlists column of the row(s) that match a specified username
            String username = enteredUser;
            String sqlSelect = "SELECT playlists FROM data WHERE username = ?";
            PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
            stmtSelect.setString(1, username);
            ResultSet rs = stmtSelect.executeQuery();

            // Update the playlists column with a new value
            String playlists = "";
            while (rs.next()) {
                playlists = rs.getString("playlists");
            }
            playlists += "||" + enteredPlaylist;
            String sqlUpdate = "UPDATE data SET playlists = ? WHERE username = ?";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, playlists);
            stmtUpdate.setString(2, username);
            int rowsAffected = stmtUpdate.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated");

            // Close the resources
            rs.close();
            stmtSelect.close();
            stmtUpdate.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
