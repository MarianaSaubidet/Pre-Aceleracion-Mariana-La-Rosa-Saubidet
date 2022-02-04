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
@Getter
@Setter

@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String name;
    private String image;
    private Long age;
    private Double weight;
    private String biography;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    //SOFT DELETE
    private boolean deleted = Boolean.FALSE;

    //ADD
    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    //REMOVE
    public void removeMovie(Movie movie) {
        this.movies.remove(movie);
    }
}
