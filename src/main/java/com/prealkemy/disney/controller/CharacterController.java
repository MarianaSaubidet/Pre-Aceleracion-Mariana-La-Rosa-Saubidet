package com.prealkemy.disney.controller;

import com.prealkemy.disney.dto.CharacterDTO;
import com.prealkemy.disney.service.CharacterService;
import com.prealkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/characters")
@Controller
public class CharacterController {


    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieService movieService;

    @Autowired
    public CharacterController(CharacterService characterService, MovieService movieService) {
        this.characterService = characterService;
        // VER ERROR
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getAll(){
        List<CharacterDTO> charactersList = this.characterService.getAll();
        return ResponseEntity.ok(charactersList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id) {
        CharacterDTO charDTO = this.characterService.getDetailById(id);
        return ResponseEntity.ok(charDTO);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<CharacterDTO> dtoList = this.characterService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO savedCharacter = characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO charDTO) {
        CharacterDTO result = this.characterService.modify(id, charDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CharacterDTO> delete(@PathVariable Long id) {
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<Void> addMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.characterService.addMovie(id, idMovie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.characterService.removeMovie(id, idMovie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long movieId, @PathVariable Long charId){
        movieService.addCharacter(movieId, charId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<Void> addGenre(@PathVariable Long movieId, @PathVariable Long genreId){
        movieService.addGenre(movieId, genreId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
