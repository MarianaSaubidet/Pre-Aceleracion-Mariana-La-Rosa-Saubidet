package com.prealkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
    public class GenreDTO {
        private Long id;

        private String name;
        private List<MovieDTO> movies;
}
