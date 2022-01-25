package com.prealkemy.disney.mapper;

import com.prealkemy.disney.dto.CharacterDTO;
import com.prealkemy.disney.dto.CharacterDTOBasic;
import com.prealkemy.disney.dto.MovieDTO;
import com.prealkemy.disney.entity.CharacterEntity;
import com.prealkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharMapper {

    @Autowired
    private MovieMapper movieMapper;

    //
    public CharacterEntity charDTO2Entity(CharacterDTO dto){
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName(dto.getName());
        characterEntity.setImage(dto.getImage());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setBiography(dto.getBiography());
        return characterEntity;
    }

    //
    public CharacterDTO charEntity2DTO(CharacterEntity characterEntity, boolean loadMovie) {
        CharacterDTO dto = new CharacterDTO();

        dto.setId(characterEntity.getId());
        dto.setName(characterEntity.getName());
        dto.setImage(characterEntity.getImage());
        dto.setAge(characterEntity.getAge());
        dto.setWeight(characterEntity.getWeight());
        dto.setBiography(characterEntity.getBiography());

        if(loadMovie) {
            List<MovieDTO> dtoList = new ArrayList<>();
            for (MovieEntity entity : characterEntity.getMovies()) {
                dtoList.add(movieMapper.movieEntity2DTO(entity,false));
            }
            dto.setMovies(dtoList);
        }
        return dto;
    }

    //
    public List<CharacterDTO> charEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovie) {
        List<CharacterDTO> dtoList = new ArrayList<>();

        for (CharacterEntity entity: entities) {
            dtoList.add(charEntity2DTO(entity, loadMovie));
        }
        return dtoList;
    }

    //
    public Set<CharacterEntity> charDTOList2EntityList(Set<CharacterDTO> dtoSet) {
        Set<CharacterEntity> entitySet = new HashSet<>();
        for (CharacterDTO dto: dtoSet) {
            entitySet.add(charDTO2Entity(dto));
        }
        return entitySet;
    }

    //
    public List<CharacterDTOBasic> basicEntityList2DTOBasicList(Collection<CharacterEntity> entities) {
        List<CharacterDTOBasic> basicList = new ArrayList<>();
        CharacterDTOBasic dtoBasic;

        for (CharacterEntity entity: entities) {
            dtoBasic = new CharacterDTOBasic();
            dtoBasic.setId(entity.getId());
            dtoBasic.setName(entity.getName());
            dtoBasic.setImage(entity.getImage());

            basicList.add(dtoBasic);
        }
        return basicList;
    }

    //
    private CharacterDTOBasic charEntity2BasicDTO(CharacterEntity charEntity) {
        CharacterDTOBasic dto = new CharacterDTOBasic();

        dto.setImage(charEntity.getImage());
        dto.setName(charEntity.getName());
        return dto;
    }
}