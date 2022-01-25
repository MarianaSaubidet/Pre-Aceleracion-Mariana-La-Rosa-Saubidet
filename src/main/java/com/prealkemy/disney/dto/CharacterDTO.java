package com.prealkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CharacterDTO {
    private Long id;

    private String name;
    private String image;
    private Long age;
    private Double weight;
    private String biography;

    private List<MovieDTO> movies;
}
