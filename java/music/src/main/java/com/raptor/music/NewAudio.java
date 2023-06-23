package com.raptor.music;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewAudio {
    @GetMapping("/newaudio")
    public String audio() {
        return "newaudio.html";

    }

    @GetMapping("/newimage")
    public String image() {
        return "newimage.html";

    }
}
