package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.SpotifyModel;


@Repository
public interface SpotifyRepositories extends CrudRepository<SpotifyModel, Long>{
    public abstract ArrayList<SpotifyModel> findByDuration(Integer duration);
    
}
