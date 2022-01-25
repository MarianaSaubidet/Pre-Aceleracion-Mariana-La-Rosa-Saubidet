package com.prealkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer star;

    private List<CharacterDTO> characters;
    private List<GenreDTO> genres;

}
