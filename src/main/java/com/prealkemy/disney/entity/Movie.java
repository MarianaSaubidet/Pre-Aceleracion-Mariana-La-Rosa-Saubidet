package com.prealkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@SQLDelete(sql = "UPDATE move SET deleted = true WHERE id=?")
@Where(clause ="deleted=false")

@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String image;
    private String title;

    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate creationDate;

    private Integer star;


    //TABLA INTERMEDIA MOVIES & CHARACTERS
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "movie_chars",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Character> characters = new ArrayList<>();

    //TABLA INTERMEDIA MOVIES & GENRES
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genres",
            joinColumns= @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

    //SOFT DELETE
    private boolean deleted = Boolean.FALSE;

    //ADD CHARACTER
    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    //REMOVE CHARACTER
    public void removeCharacter(Character character) {
        this.characters.remove(character);
    }

    // ADD GENRE
    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    //REMOVE GENRE
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final Movie other = (Movie) obj;
        return other.id == this.id;
    }
}
