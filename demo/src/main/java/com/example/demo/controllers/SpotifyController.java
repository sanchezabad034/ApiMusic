package com.example.demo.controllers;

import com.example.demo.repositories.SpotifyRepositories;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.SpotifyModel;
import com.example.demo.services.SpotifyServices;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/spotify")
public class SpotifyController {
    @Autowired
    SpotifyServices spotifyServices; 

    @GetMapping()
    public ArrayList<SpotifyModel> getsong(){
        return spotifyServices.getSongs();
    }

    @PostMapping()
    public SpotifyModel savesong(@RequestBody SpotifyModel song){
        return this.spotifyServices.saveSongs(song);
    }

    @GetMapping(path = "/{id}")
    public Optional<SpotifyModel> getbyID(@PathVariable("id") Long id){
        return this.spotifyServices.getID(id); 
    }

    @GetMapping("/query")
    public ArrayList<SpotifyModel> getSongByDuration(@RequestParam("duration") Integer duration){
        return this.spotifyServices.getforDuration(duration); 
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.spotifyServices.deleteSong(id); 
        if(ok){
            return "Se elimino la canción " + id; 
        }else{
            return "No se pudo eliminar la canción " + id; 
        }
    }

   @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public ResponseEntity<String> handleTraceRequest(@RequestBody String requestData) {
        return ResponseEntity.ok("Request data: " + requestData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpotifyModel> updateSong(@PathVariable("id") Long id, @RequestBody SpotifyModel updatedSong) {
        Optional<SpotifyModel> existingSongOpt = spotifyServices.getID(id);
        if (existingSongOpt.isPresent()) {
            SpotifyModel existingSong = existingSongOpt.get();


            if (updatedSong.getTitle() != null) {
                existingSong.setTitle(updatedSong.getTitle());
            }
            if (updatedSong.getDuration() != null) {
                existingSong.setDuration(updatedSong.getDuration());
            }
            if (updatedSong.getArtist() != null) {
                existingSong.setArtist(updatedSong.getArtist());
            }


            SpotifyModel updatedSongEntity = spotifyServices.saveSongs(existingSong);
            return ResponseEntity.ok(updatedSongEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public ResponseEntity<SpotifyModel> patchMethod(@RequestBody SpotifyModel updatedSong) {
        Long id = updatedSong.getId();
        Optional<SpotifyModel> existingSongOpt = spotifyServices.getID(id);

        if (existingSongOpt.isPresent()) {
            SpotifyModel songToUpdate = existingSongOpt.get();

            
            if (updatedSong.getTitle() != null) {
                songToUpdate.setTitle(updatedSong.getTitle());
            }
            if (updatedSong.getDuration() != null) {
                songToUpdate.setDuration(updatedSong.getDuration());
            }
            if (updatedSong.getArtist() != null) {
                songToUpdate.setArtist(updatedSong.getArtist());
            }

            SpotifyModel updatedSongEntity = spotifyServices.saveSongs(songToUpdate);

            return ResponseEntity.ok(updatedSongEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Allow", "GET, POST, PUT, DELETE, HEAD, OPTIONS, TRACE, PATCH");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
    

}
