package com.raptor.music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class User {

    @GetMapping("/user")
    public String user(Model model) {
        String test = getSql().toString();
        model.addAttribute("message", test);

        return "user";
    }

    public static ArrayList<String> getSql() {    
    ArrayList<String> data = new ArrayList<String>();

        String url = "jdbc:mysql://localhost:3306/users"; // replace "mydatabase" with your database name
        String username = "Tyler"; // replace "myuser" with your MySQL username
        String password = "Blackrobin7"; // replace "mypassword" with your MySQL password

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM userdata"; // replace "mytable" with your table name
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        int id = rs.getInt("id"); // replace "id" with your column name
                        String name = rs.getString("username"); // replace "name" with your column name
                        String userpassword=rs.getString("password");
                        String playlistData=rs.getString("songs");
                        
                        
                        
                        // do something with the retrieved data
                        data.add('['+String.valueOf(id));
                        data.add(name);
                        data.add(userpassword);
                        data.add(playlistData+']');
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
