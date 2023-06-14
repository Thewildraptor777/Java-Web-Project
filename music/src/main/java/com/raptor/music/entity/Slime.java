package com.raptor.music.entity;

import javax.persistence.Entity;  
import javax.persistence.Table;

import jakarta.persistence.Column;     
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;   
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
@Table(name = "slime")
public class Slime {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long id;

  @Column(nullable = false, updatable = false)     
  private String title;
  
  public String getTitle() {
    return title;    
  }
  
  @Transient     
  public void setTitle(String title) {
   this.title = title;    
  }
}