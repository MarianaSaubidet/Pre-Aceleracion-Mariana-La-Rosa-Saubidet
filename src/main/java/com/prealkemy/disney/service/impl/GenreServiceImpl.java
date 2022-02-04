package com.prealkemy.disney.service.impl;

import com.prealkemy.disney.dto.GenreDTO;
import com.prealkemy.disney.entity.Genre;
import com.prealkemy.disney.exception.ParamNotFound;
import com.prealkemy.disney.mapper.GenreMapper;
import com.prealkemy.disney.repository.GenreRepository;
import com.prealkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public GenreDTO save(GenreDTO dto){
        //TODO: Guardar Género

        // Convierto ContinentDTO a entity
        Genre genreEntity = genreMapper.genreDTO2Entity(dto);

        //la entidad guardada con Id, imagen, y nombre, en forma de entidad.
        Genre genreSaved = genreRepository.save(genreEntity);

        //Como el controller maneja DTO debemos convertirla en un DTO.
        //Vuelvo a convertir la entidad guardada en DTO.
        GenreDTO result = genreMapper.genreEntity2DTO(genreSaved);
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities);
        return result;
    }

    @Override
    public void deleteGenreById(Long id) {
        this.genreRepository.deleteById(id);
       }

    @Override
    public GenreDTO modify(Long id, GenreDTO genreDTO) {
        Genre savedGenre = this.getById(id);
        savedGenre.setName(genreDTO.getName());
        Genre editedGenre = genreRepository.save(savedGenre);
        GenreDTO result = genreMapper.genreEntity2DTO(editedGenre);
        return result;
    }

    private Genre getById(Long id) {
        Optional<Genre> toBeFound = genreRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("THIS GENRE DOES NOT EXIST: " + id);
        }
        return toBeFound.get();
    }

}
