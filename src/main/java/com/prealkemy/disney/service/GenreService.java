package com.prealkemy.disney.service;

import com.prealkemy.disney.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    GenreDTO save(GenreDTO genreDTO);

    List<GenreDTO> getAllGenres();

    void deleteGenreById(Long id);

    GenreDTO modify(Long id, GenreDTO genreDTO);
}
