package com.prealkemy.disney.service.impl;

import com.prealkemy.disney.dto.CharacterDTO;
import com.prealkemy.disney.dto.CharacterDTOBasic;
import com.prealkemy.disney.dto.CharacterDTOFilter;
import com.prealkemy.disney.entity.Character;
import com.prealkemy.disney.entity.Movie;
import com.prealkemy.disney.exception.ParamNotFound;
import com.prealkemy.disney.mapper.CharMapper;
import com.prealkemy.disney.mapper.MovieMapper;
import com.prealkemy.disney.repository.CharacterRepository;
import com.prealkemy.disney.repository.specification.CharacterSpecification;
import com.prealkemy.disney.service.CharacterService;
import com.prealkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharMapper charMapper;
    @Autowired
    private CharacterRepository charRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CharacterSpecification charSpecification;

    @Override
    public List<CharacterDTO> getAll() {
        List<Character> charEntities = charRepository.findAll();
        List<CharacterDTO> dtoList = charMapper.charEntityList2DTOList(charEntities, true);
        return dtoList;
    }

    @Override
    public List<CharacterDTOBasic> getBasicCharList() {
        List<Character> charList = charRepository.findAll();
        List<CharacterDTOBasic> resultDTO = charMapper.basicEntityList2DTOBasicList(charList);
        return resultDTO;
    }

    @Override
    public CharacterDTO modify(Long id, CharacterDTO charDTO) {
        Character savedChar = this.getById(id);

        savedChar.setName(charDTO.getName());
        savedChar.setImage(charDTO.getImage());
        savedChar.setAge(charDTO.getAge());
        savedChar.setWeight(charDTO.getWeight());
        savedChar.setBiography(charDTO.getBiography());

        savedChar.setMovies(movieMapper.movieDTOList2EntityList(charDTO.getMovies(), false));

        Character editedChar = charRepository.save(savedChar);

        CharacterDTO savedDTO = charMapper.charEntity2DTO(editedChar, false);

        return savedDTO;
    }

    @Override
    public CharacterDTO save(CharacterDTO charDTO) {
        Character charEntity = new Character();

        charEntity.setName(charDTO.getName());
        charEntity.setImage(charDTO.getImage());
        charEntity.setAge(charDTO.getAge());
        charEntity.setWeight(charDTO.getWeight());
        charEntity.setBiography(charDTO.getBiography());

        Character savedChar = charRepository.save(charEntity);
        CharacterDTO savedDTO = charMapper.charEntity2DTO(savedChar, false);

        return savedDTO;
    }

    @Override
    public Character getById(Long id) {
        Optional<Character> character = charRepository.findById(id);
        if(!character.isPresent()) {
            throw new ParamNotFound("THIS CHARACTER DOES NOT EXIST.");
        }
        return character.get();
    }

    @Override
    public void delete(Long id) {
        charRepository.deleteById(id);
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order) {
        CharacterDTOFilter charFilter = new CharacterDTOFilter(name, age, movies, order);

        List<Character> entities = this.charRepository.findAll(this.charSpecification.getByFilters(charFilter));
        List<CharacterDTO> result = this.charMapper.charEntityList2DTOList(entities,true);

        return result;
    }

    @Override
    public CharacterDTO getDetailById(Long id) {
        Character charEntity = this.getById(id);
        CharacterDTO result = charMapper.charEntity2DTO(charEntity, true);
        return result;
    }

    @Override
    public void addMovie(Long id, Long idMovie) {
        Character charEntity = charRepository.getById(id);
        charEntity.getMovies().size();
        Movie movie = this.movieService.getById(idMovie);
        charEntity.addMovie(movie);
        this.charRepository.save(charEntity);
    }

    @Override
    public void removeMovie(Long id, Long idMovie) {
        Character charEntity = charRepository.getById(id);
        charEntity.getMovies().size();
        Movie movie = this.movieService.getById(idMovie);
        charEntity.removeMovie(movie);
        this.charRepository.save(charEntity);
    }

}
