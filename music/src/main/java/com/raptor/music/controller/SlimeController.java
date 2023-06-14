package com.raptor.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raptor.music.entity.Slime;
import com.raptor.music.repository.SlimeRepository;

@RestController
public class SlimeController {

  @Autowired
  SlimeRepository slimeRepository;
  
  @GetMapping("/slimes/ids")   
  public List<Slime> getSlimesByIds(@RequestParam List<Long> ids) {
    return slimeRepository.findByIds(ids);
  }
}