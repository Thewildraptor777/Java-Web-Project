package com.raptor.music;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.raptor.music.user.UserSql;

@Controller
public class MainPage {
    private static final String INDEX_PAGE = "index.html";

    @GetMapping("/user/{name}")
    public String mainPage(Model model, @PathVariable("name") String userName,
            @RequestParam(value = "playlist", required = false, defaultValue = "") String playlistName) {

        // Get the list of users from the database
        String userListString = UserSql.read()[0];

        // Check if the requested user is in the list of users
        if (userListString.contains(userName)) {
            model.addAttribute("playlist", playlistName);
            model.addAttribute("name", userName);
        } else {
            model.addAttribute("wronguser", "true");
        }

        return INDEX_PAGE;
    }
}