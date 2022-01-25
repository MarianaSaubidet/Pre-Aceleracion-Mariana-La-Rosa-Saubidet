package com.prealkemy.disney.service;

import com.prealkemy.disney.dto.CharacterDTO;
import com.prealkemy.disney.dto.CharacterDTOBasic;
import com.prealkemy.disney.entity.CharacterEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CharacterService {
    public List<CharacterDTO> getAll();
    List<CharacterDTOBasic> getBasicCharList();

    public void delete(Long id);

    public CharacterDTO getDetailById(Long id);

    public CharacterDTO modify(Long id, CharacterDTO charDTO);

    public CharacterDTO save(CharacterDTO charDTO);

    public CharacterEntity getById(Long id);

    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order);

    public void addMovie(Long id, Long idMovie);

    public void removeMovie(Long id, Long idMovie);

}
