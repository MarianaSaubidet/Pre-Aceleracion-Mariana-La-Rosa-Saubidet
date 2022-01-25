package com.prealkemy.disney.mapper;

import com.prealkemy.disney.dto.GenreDTO;
import com.prealkemy.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genreEntity){
        GenreDTO dto = new GenreDTO();
        dto.setId(genreEntity.getId());
        dto.setName(genreEntity.getName());
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtos = new ArrayList<>();

        for (GenreEntity entity : entities) {
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }
}
