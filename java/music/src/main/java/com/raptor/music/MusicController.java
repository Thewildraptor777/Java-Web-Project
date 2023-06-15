package com.raptor.music;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MusicController {
    
    @GetMapping("/")        
    public String myPage(Model model) {
        
        System.out.println(MusicData.data("slime").get(0));
        model.addAttribute("myList", MusicData.data("slime"));
        return "my-page";
    }
    
}