package com.raptor.music.music;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/uploadmusic")
    @ResponseBody
    public String uploadmusic(@RequestParam("file") MultipartFile file) {
        try {
            // Check file type
            String fileType = file.getContentType();
            if (fileType != null && !fileType.equals("audio/mpeg") && !fileType.equals("audio/wav")
                    && !fileType.equals("audio/ogg")) {
                return "Only audio files with extensions .mp3, .wav, or .ogg are allowed!";
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths
                    .get("C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\static\\data\\music\\"
                            + file.getOriginalFilename());
            Files.write(path, bytes);

            return "http://localhost:8080/data/music/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }

    @PostMapping("/uploadimage")
    @ResponseBody
    public String uploadimage(@RequestParam("file") MultipartFile file) {
        try {
            // Check file type
            String fileType = file.getContentType();
            if (fileType != null && !fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
                return "Only image files with extensions .jpg, .jpeg, .png, or .gif are allowed!";
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths
                    .get("C:\\Code\\Repos\\Java Web Project\\java\\music\\src\\main\\resources\\static\\data\\images\\"
                            + file.getOriginalFilename());
            Files.write(path, bytes);

            return "http://localhost:8080/data/images/" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
}