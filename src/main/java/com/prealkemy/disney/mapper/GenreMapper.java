package com.prealkemy.disney.mapper;

import com.prealkemy.disney.dto.GenreDTO;
import com.prealkemy.disney.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    @Autowired
    private MovieMapper movieMapper;

    public Genre genreDTO2Entity(GenreDTO dto) {
        Genre genreEntity = new Genre();
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(Genre genreEntity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genreEntity.getId());
        dto.setName(genreEntity.getName());
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<Genre> entities) {
        //    List<GenreDTO> dtos = new ArrayList<>();
        //    for (Genre entity : entities) {
        //        dtos.add(this.genreEntity2DTO(entity));
        //    }
        //    return dtos;
        //}

        return entities.stream().map(entity -> genreEntity2DTO(entity)).collect(Collectors.toList());
        //return genreEntities.stream().map(genre -> genreEntity2DTO(genre) ).collect(Collectors.toList());
    }
}