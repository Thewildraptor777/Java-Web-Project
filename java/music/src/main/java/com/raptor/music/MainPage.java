package com.raptor.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.raptor.music.user.userSql;

@Controller
public class MainPage {
    @GetMapping("/music/{name}")
    public String mainpage(Model model, @PathVariable("name") String user) {
        String userListString = userSql.read()[0];

        //
        String s = userListString;

        List<String> userList = new ArrayList<String>(Arrays.asList(s.split("  ")));

        if (userList.indexOf(user) != -1) {

        } else {

            model.addAttribute("wronguser", "true");

        }
        return "index.html";
    }

}
