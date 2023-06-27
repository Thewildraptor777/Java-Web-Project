package com.raptor.music.music.createNew;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewAudio {
    @GetMapping("/newaudio")
    public String audio() {
        return "newMusic/newaudio.html";

    }

    @GetMapping("/newimage")
    public String image() {
        return "newMusic/newimage.html";

    }
}
