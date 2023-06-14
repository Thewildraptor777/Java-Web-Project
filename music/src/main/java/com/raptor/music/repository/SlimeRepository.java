package com.raptor.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raptor.music.entity.Slime;

public interface SlimeRepository extends JpaRepository<Slime, Long> {

  List<Slime> findByTitleIgnoreCaseContaining(String title);
  
  List<Slime> findByIds(List<Long> ids);   
}
