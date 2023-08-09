package com.example.demo.Models;

import jakarta.persistence.*;

@Entity
@Table(name="songs")

public class SpotifyModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id; 

    private String title; 
    private Integer duration; 
    private String artist; 

    //Establecer metodos getter and setter 
    public void setTitle(String title){
        this.title = title; 
    }

    public String getTitle(){
        return title; 
    }

    public Long getId(){
        return id; 
    }

    public void setId(Long id){
        this.id = id; 
    }

    public void setDuration(Integer duration){
        this.duration = duration; 
    }

    public Integer getDuration(){
        return duration; 
    }

    public void setArtist(String artist){
        this.artist = artist; 
    }

    public String getArtist(){
        return artist; 
    }
}
