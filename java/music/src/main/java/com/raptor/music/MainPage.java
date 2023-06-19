package com.raptor.music;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class MainPage {
   @GetMapping("/{name}")
public String page(Model model, @PathVariable("name") String name){
    model.addAttribute("test", name);
    return "index.html";
}

}
