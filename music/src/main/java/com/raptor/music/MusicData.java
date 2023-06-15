package com.raptor.music;

import java.sql.*;
import java.util.ArrayList;

public class MusicData {
    public static ArrayList<String> data(String playlist){        
        return name(playlist);
    }
    private static ArrayList<String> name(String playlist) {
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> links = new ArrayList<String>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> images = new ArrayList<String>();
        ArrayList<String> artists = new ArrayList<String>();
        ArrayList<String> masterArrayList = new ArrayList<String>();

        String url = "jdbc:mysql://localhost:3306/music"; // Replace "mydatabase" with your database name
        String user = "Tyler"; // Replace "root" with your MySQL username
        String password = "Blackrobin7"; // Replace "mypassword" with your MySQL password

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

        return masterArrayList;
    }
private static String StringArraytoString(ArrayList<String> list){
    String text="";
    for(int i=0;i<list.size();i++){
        text+=list.get(i)+",";
    }
    return text;
}
private static String IntegerArraytoString(ArrayList<Integer> list){
    String text="";
    for(int i=0;i<list.size();i++){
        text+=list.get(i).toString()+",";
    }
    return text;
}
}
