package com.prealkemy.disney.controller;

import com.prealkemy.disney.dto.GenreDTO;
import com.prealkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/genres")
@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    //-------------------VER ESTA PARTE CON TUTOR ---------------------
    //@Autowired
    //GenreController(GenreService genreService) {
    //    this.genreService = genreService;
    //}
    //-------------------VER ESTA PARTE CON TUTOR ---------------------

    // EndPoint para obtener los generos de peliculas
    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> genresList = genreService.getAllGenres();
        return ResponseEntity.ok().body(genresList);
    }

    // EndPoint para guardar generos de peliculas
    @PostMapping
    public ResponseEntity<GenreDTO> save(@Valid @RequestBody GenreDTO genreDTO){
        GenreDTO savedGenre = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    //---------------Ver con Nico Careri---------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        genreService.deleteGenreById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //---------------Ver con Nico Careri---------------------------

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> modify(@PathVariable Long id, @RequestBody GenreDTO genreDTO){
        GenreDTO editedGenre = genreService.modify(id, genreDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenre);
    }

    //@PutMapping("/{id}")
    //public ResponseEntity<GenderDTO> modify(@RequestBody GenderDTO genderDTO, @PathVariable Long id){
    //    GenderDTO gender = genderService.modify(genderDTO,id);
    //    return ResponseEntity.status(HttpStatus.CREATED).body(gender);

}
