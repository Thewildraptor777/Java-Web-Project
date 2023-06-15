package com.raptor.music.displayData;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class MusicData {
    public static String data(String playlist) {
        return name(playlist);
    }
    @Value("${spring.datasource.url}")    
    private static String springUrl;
    @Value("${spring.datasource.username}")    
    private static String springUser;
    @Value("${spring.datasource.passwrod}")    
    private static String SpringPassword;

    private static String name(String playlist) {
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> links = new ArrayList<String>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> images = new ArrayList<String>();
        ArrayList<String> artists = new ArrayList<String>();
        ArrayList<String> masterArrayList = new ArrayList<String>();

        String url = springUrl; // Replace "mydatabase" with your database name
        String user = springUser; // Replace "root" with your MySQL username
        String password = SpringPassword; // Replace "mypassword" with your MySQL password

        // Establish database connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Create SQL statement
            String sql = "SELECT * FROM " + playlist; // Replace "mytable" with your table name
            try (Statement stmt = conn.createStatement()) {
                // Execute SQL query
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    // Process query results
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String link = rs.getString("Link");
                        String image = rs.getString("image");
                        String title = rs.getString("title");
                        String artist = rs.getString("Artist");
                        ids.add(id);
                        links.add(link);

                        images.add(image);
                        titles.add(title);

                        artists.add(artist);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        masterArrayList.add("[" + IntegerArraytoString(ids) + "]");
        masterArrayList.add("[" + StringArraytoString(links) + "]");
        masterArrayList.add("[" + StringArraytoString(images) + "]");
        masterArrayList.add("[" + StringArraytoString(titles) + "]");
        masterArrayList.add("[" + StringArraytoString(artists) + "]");

        return masterArrayList.toString();
    }

    private static String StringArraytoString(ArrayList<String> list) {
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            text += '\"'+list.get(i)+ '\"' + ",";
        }
                text=deleteCommma(text);

        return text;
    }

    private static String IntegerArraytoString(ArrayList<Integer> list) {
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            text += '\"'+ list.get(i).toString() + '\"'+ ",";
        }
        text=deleteCommma(text);
        return text;
    }
    private static String deleteCommma(String str) {
    if (str == null || str.length() < 2) {
        return str; // Return the original string if it is null or shorter than 2 characters
    } else {
        int index = str.length() - 1; // Calculate the index of the second to last character
        String firstPart = str.substring(0, index);
        String secondPart = str.substring(index + 1);
        return firstPart + secondPart; // Concatenate the two parts
    }
}
}
