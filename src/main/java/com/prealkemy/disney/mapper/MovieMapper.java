package com.prealkemy.disney.mapper;

import com.prealkemy.disney.dto.MovieDTO;
import com.prealkemy.disney.dto.MovieDTOBasic;
import com.prealkemy.disney.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private CharMapper charMapper;

    @Autowired
    private GenreMapper genreMapper;

    public Movie movieDTO2Entity(MovieDTO dto, boolean loadChar) {
        Movie movieEntity = new Movie();

        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());

        //CASTEO DE STRING A FECHA
        String date = dto.getCreationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        movieEntity.setCreationDate(transformedDate);

        movieEntity.setStar(dto.getStar());
        return movieEntity;
    }

    //ESTO EVITA EL BUCLE INFINITO Y PUEDO MOSTRARLO
    public MovieDTO movieEntity2DTO(Movie movieEntity, boolean loadChar) {
        MovieDTO dto = new MovieDTO();

        dto.setId(movieEntity.getId());
        dto.setImage(movieEntity.getImage());
        dto.setTitle(movieEntity.getTitle());

        LocalDate date = movieEntity.getCreationDate();//1. Get the original format date
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); //2. Convert it to string
        dto.setCreationDate(formatDate);

        dto.setStar(movieEntity.getStar());

        //
        if(loadChar) {
            dto.setCharacters(charMapper.charEntityList2DTOList(movieEntity.getCharacters(), false));
            dto.setGenres(genreMapper.genreEntityList2DTOList(movieEntity.getGenres()));
        }

        return dto;

    }

    public List<MovieDTO> movieEntityList2DTOList(List<Movie> entities, boolean load) {
        List<MovieDTO> dtos = new ArrayList<>();

        for (Movie entity: entities) {
            dtos.add(this.movieEntity2DTO(entity, load));
        }
        return dtos;
    }

    public List<Movie> movieDTOList2EntityList(List<MovieDTO> dtoList, boolean load) {
        List<Movie> entities = new ArrayList<>();

        for (MovieDTO dto: dtoList) {
            entities.add(this.movieDTO2Entity(dto, load));
        }
        return entities;
    }

    public MovieDTOBasic entity2BasicDTO(Movie movieEntity) {
        MovieDTOBasic dto = new MovieDTOBasic();

        dto.setImage(movieEntity.getImage());
        dto.setTitle(movieEntity.getTitle());

        LocalDate date = movieEntity.getCreationDate();//1. Get the original format date
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); //2. Convert it to string
        dto.setCreationDate(formatDate);

        return dto;
    }

    public List<MovieDTOBasic> entityList2BasicDTO(List<Movie> entities) {
        List<MovieDTOBasic> newList = new ArrayList<>();
        for(Movie entity : entities) {
            newList.add(this.entity2BasicDTO(entity));
        }
        return newList;
    }
}
