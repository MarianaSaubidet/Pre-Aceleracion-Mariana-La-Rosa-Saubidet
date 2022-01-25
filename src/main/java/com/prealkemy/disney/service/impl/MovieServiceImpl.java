package com.prealkemy.disney.service.impl;

import com.prealkemy.disney.dto.MovieDTO;
import com.prealkemy.disney.dto.MovieDTOBasic;
import com.prealkemy.disney.dto.MovieDTOFilter;
import com.prealkemy.disney.entity.CharacterEntity;
import com.prealkemy.disney.entity.MovieEntity;
import com.prealkemy.disney.exception.ParamNotFound;
import com.prealkemy.disney.mapper.CharMapper;
import com.prealkemy.disney.mapper.MovieMapper;
import com.prealkemy.disney.repository.MovieRepository;
import com.prealkemy.disney.repository.specification.MovieSpecification;
import com.prealkemy.disney.service.CharacterService;
import com.prealkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharMapper charMapper;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieSpecification movieSpecification;

    @Override
    public MovieDTO save(MovieDTO movie) {
        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movie, true);
        MovieEntity movieSaved = movieRepository.save(movieEntity);
        MovieDTO result = movieMapper.movieEntity2DTO(movieSaved, false);

        return result;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities, true);

        return result;
    }

    @Override
    public List<MovieDTOBasic> getBasicList() {
        List<MovieEntity> movieList = movieRepository.findAll();
        List<MovieDTOBasic> resultDTO = movieMapper.entityList2BasicDTO(movieList);
        return resultDTO;
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO modify(Long id, MovieDTO movieDTO) {
        MovieEntity savedMovie = this.getById(id);

        savedMovie.setImage(movieDTO.getImage());
        savedMovie.setTitle(movieDTO.getTitle());

        String date = movieDTO.getCreationDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        savedMovie.setCreationDate(transformedDate);

        savedMovie.setStar(movieDTO.getStar());

        MovieEntity movieEntity = movieRepository.save(savedMovie);
        MovieDTO result = movieMapper.movieEntity2DTO(movieEntity, false);

        return result;
    }

    @Override
    public void addCharacter(Long movieId, Long characterId) {
        MovieEntity movieEntity = this.getById(movieId);
        movieEntity.getCharacters().size();

        CharacterEntity character = characterService.getById(characterId);
        movieEntity.addCharacter(character);
        movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieDTOFilter movieFilters = new MovieDTOFilter(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll(movieSpecification.getFiltered(movieFilters));
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entityList, true);
        return result;
    }

    @Override
    public MovieEntity getById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);
        if(!movieEntity.isPresent()) {
            throw new ParamNotFound("THIS MOVIE DOES NOT EXIST.");
        }
        return movieEntity.get();
    }

    @Override
    public MovieDTO getByDetails(Long id) {
        MovieEntity movieEntity = this.getById(id);
        MovieDTO result = movieMapper.movieEntity2DTO(movieEntity, true);
        return result;
    }

    @Override
    public void addGenre(Long movieId, Long genreId) {

    }
}
