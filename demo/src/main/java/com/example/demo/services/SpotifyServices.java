package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.SpotifyModel;
import com.example.demo.repositories.SpotifyRepositories;

@Service
public class SpotifyServices {
    @Autowired
    SpotifyRepositories spotifyRepositories; 

    public ArrayList<SpotifyModel> getSongs(){
        return (ArrayList<SpotifyModel>) spotifyRepositories.findAll(); 
    }

    public SpotifyModel saveSongs(SpotifyModel songs){
        return spotifyRepositories.save(songs); 
    }

    public Optional<SpotifyModel> getID(Long id ){
        return spotifyRepositories.findById(id); 
    }


    public ArrayList<SpotifyModel> getforDuration(Integer duration){
        return spotifyRepositories.findByDuration(duration); 
    }

    public boolean deleteSong(Long id){
        try{
            spotifyRepositories.deleteById(id);
            return true; 
        }catch(Exception err){
            return false; 
        }

    }

}
