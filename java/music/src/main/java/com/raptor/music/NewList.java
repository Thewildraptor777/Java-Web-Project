package com.raptor.music;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewList {
    @GetMapping("/newList")
    public String list(){
        return "playlist.html";
        
    }
}
