package com.prealkemy.disney.service;

import com.prealkemy.disney.dto.MovieDTO;
import com.prealkemy.disney.dto.MovieDTOBasic;
import com.prealkemy.disney.entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MovieService {

    //Listado de todas las Peliculas
    List<MovieDTO> getAllMovies();

    //Listado Basico de las Peliculas (DTOBasic)
    List<MovieDTOBasic> getBasicList();

    //SOFT DELETE
    void delete(Long id);

    //UPDATE
    MovieDTO modify(Long id, MovieDTO movieDTO);

    //SAVE MOVIE
    MovieDTO save(MovieDTO movie);

    void addCharacter(Long movieId, Long characterId);

    MovieEntity getById(Long id);

    MovieDTO getByDetails(Long id);

    void addGenre(Long movieId, Long genreId);

    List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);











}
