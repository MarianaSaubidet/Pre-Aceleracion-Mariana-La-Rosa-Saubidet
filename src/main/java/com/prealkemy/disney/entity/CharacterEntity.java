package com.prealkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

@Getter
@Setter

public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Column(nullable = false)
    private String name;
    private String image;
    private Long age;
    private Double weight;
    private String biography;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();

    //SOFT DELETE
    private Boolean deleted = Boolean.FALSE;

    //ADD
    public void addMovie(MovieEntity movie) {
        this.movies.add(movie);
    }

    //REMOVE
    public void removeMovie(MovieEntity movie) {
        this.movies.remove(movie);
    }
}
