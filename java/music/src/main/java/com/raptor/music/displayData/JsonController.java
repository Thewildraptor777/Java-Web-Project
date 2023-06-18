package com.raptor.music.displayData;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @Autowired
    private MusicData musicData;
    
    @GetMapping("/data")
    public ResponseEntity<String> getJson() throws IOException {
String playlistData = musicData.data("slime");
        String json = playlistData;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

}