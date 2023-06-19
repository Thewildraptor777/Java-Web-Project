package com.raptor.music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Singup {
    @GetMapping("/")
    public String userpage(Model model) {
        return "user.html";
    }

    @GetMapping("/signup")
    public String page(Model model) {
        return "user.html";
    }

    @PostMapping("/submit-form")
    public String submitForm(Model model, @RequestParam("name") String name) {
        // Retrieve the list of user names from the database
        String userListString = userSql.read();
        List<String> userList = new ArrayList<String>(Arrays.asList(userListString.split("  ")));

        // Add the new user name to the list
        userList.add(name);

        // Write the updated list of user names to the database
        userSql.write(name);

        // Add the "location" attribute to the model
        model.addAttribute("location", name);

        // Redirect to the "redirect.html" template
        return "redirect.html";
    }
}
