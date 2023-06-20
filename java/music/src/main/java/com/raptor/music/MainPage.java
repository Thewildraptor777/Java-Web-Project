package com.raptor.music;

import java.util.ArrayList;  
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.raptor.music.user.PasswordUtils;
import com.raptor.music.user.userSql;

@Controller
public class MainPage {

     @PostMapping("/music")
    public String testpage(Model model, @RequestParam("name") String name,@RequestParam("password") String pass) {
        String userListString = userSql.read()[0];
        String pList=userSql.read()[1];
                List<String> passList = new ArrayList<String>(Arrays.asList(pList.split("  ")));

        //    
        String s = userListString;

        List<String> userList = new ArrayList<String>(Arrays.asList(s.split("  ")));   
      
        if (userList.indexOf(name) != -1) {    
            if (PasswordUtils.checkPassword(pass, passList.get(userList.indexOf(name)))) {
            } else {    
                model.addAttribute("wronguser", "true");
            }
        } else {
            model.addAttribute("wronguser", "true");  
        }
        model.addAttribute("test", name);
        return "index.html";
    }
}
