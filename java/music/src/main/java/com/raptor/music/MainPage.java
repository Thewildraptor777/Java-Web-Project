package com.raptor.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainPage {

    @GetMapping("/{name}")
    public String page(Model model, @PathVariable("name") String name) {

        String userListString = userSql.read();

        //
        String s = userListString;

        List<String> userList = new ArrayList<String>(Arrays.asList(s.split("  ")));
        //
        if (userList.contains(name) == true) {
            System.out.println(name + " already exist");
        } else if (name.length() <= 15 || name == "signup") {
            model.addAttribute("usercreation", "true");
        } else {
            model.addAttribute("wronguser", "true");
        }
        model.addAttribute("test", name);
        return "index.html";
    }
}
