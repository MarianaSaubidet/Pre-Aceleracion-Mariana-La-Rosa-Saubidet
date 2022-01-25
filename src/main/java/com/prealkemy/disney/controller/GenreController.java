package com.prealkemy.disney.controller;

import com.prealkemy.disney.dto.GenreDTO;
import com.prealkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("genres")
@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    //VER ESTA PARTE CON TUTOR
    //public GenreController(GenreService genreService) {
    //    this.genreService = genreService;
    //}
    //VER ESTA PARTE CON TUTOR

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll(){

        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genreDTO){
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        genreService.deleteGenreById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> modify(@PathVariable Long id, @RequestBody GenreDTO genreDTO){
        GenreDTO editedGenre = genreService.modify(id, genreDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenre);
    }

}
