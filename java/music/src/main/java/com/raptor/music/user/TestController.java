package com.raptor.music.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @PostMapping("/test")
    public String testPage(Model model, @RequestParam("name") String name, @RequestParam("password") String password) {
        String[] userSqlResult = UserSql.read();
        String userListString = userSqlResult[0];
        String passwordListString = userSqlResult[1];

        List<String> passwordList = new ArrayList<String>(Arrays.asList(passwordListString.split("  ")));
        List<String> userList = new ArrayList<String>(Arrays.asList(userListString.split("  ")));

        int userIndex = userList.indexOf(name);
        if (userIndex != -1 && PasswordUtils.checkPassword(password, passwordList.get(userIndex))) {
            model.addAttribute("message", "Login successful");
        } else {
            model.addAttribute("message", "Incorrect username or password");
        }
        model.addAttribute("test", name);
        return "user/test.html";
    }
}