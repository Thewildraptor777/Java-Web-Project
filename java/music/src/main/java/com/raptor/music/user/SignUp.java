package com.raptor.music.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUp {
    @GetMapping("/")
    public String userpage(Model model) {
        return "user/login.html";
    }

    @GetMapping("/{name}")
    public String redirect(Model Model) {
        return "user/redirect.html";
    }

    @GetMapping("/signup")
    public String page(Model model) {
        return "user/user.html";
    }

    @PostMapping("/submit-form")
    private String submitForm(Model model, @RequestParam("name") String name, @RequestParam("pass") String password) {
        // Retrieve the list of user names from the database
        String userListString = UserSql.read()[0];
        List<String> userList = new ArrayList<String>(Arrays.asList(userListString.split("  ")));
        if (userList.contains(name)) {
            model.addAttribute("result", "user exists");
        } else {
            // Add the new user name to the list
            userList.add(name);
            password = PasswordUtils.hashPassword(password);
            // Write the updated list of user names to the database
            UserSql.write(name, password);

        }

        return "user/redirect.html";
    }

}
