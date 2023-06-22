package com.raptor.music.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Music {

    @GetMapping("/playlists/")
    @ResponseBody
    public Map<String, String> test() {
        String data = "";
        Map<String, String> response = new HashMap<>();
        response.put("data", data);
        return response;
    }

    @GetMapping("/playlists/{playlist}")
    @ResponseBody
    public Map<String, String> playlist(@PathVariable("playlist") String playlist) {
        String data = read(playlist);
        Map<String, String> response = new HashMap<>();
        response.put("data", data);
        return response;
    }

    public static String read(String playlist) {
        ArrayList<String> finalArray = new ArrayList<String>();

        ArrayList<String> idArray = new ArrayList<String>();
        ArrayList<String> linkArray = new ArrayList<String>();
        ArrayList<String> imageArray = new ArrayList<String>();
        ArrayList<String> titleArray = new ArrayList<String>();
        ArrayList<String> artistArray = new ArrayList<String>();

        try {
            // create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/music";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "Tyler", "Blackrobin7");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM " + playlist;

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("id");
                String link = rs.getString("link");
                String image = rs.getString("image");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                idArray.add(String.valueOf(id));
                linkArray.add('\"' + link + '\"');
                imageArray.add('\"' + image + '\"');
                titleArray.add('\"' + title + '\"');
                artistArray.add('\"' + artist + '\"');

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        finalArray.add(idArray.toString());
        finalArray.add(linkArray.toString());
        finalArray.add(imageArray.toString());
        finalArray.add(titleArray.toString());
        finalArray.add(artistArray.toString());
        return finalArray.toString();
    }
}