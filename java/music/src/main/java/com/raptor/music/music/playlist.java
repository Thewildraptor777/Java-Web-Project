package com.raptor.music.music;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raptor.music.user.playlistSQL;

@Controller
public class playlist {

    @GetMapping("/user/{name}/data")
    @ResponseBody
    public Map<String, String> test(@PathVariable("name") String user) {
        String data = playlistSQL.connect(user);
        Map<String, String> response = new HashMap<>();
        response.put("data", data);
        return response;
    }
}