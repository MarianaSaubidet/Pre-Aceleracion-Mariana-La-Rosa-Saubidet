package com.prealkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTOFilter {
    private String title;
    private Set<Long> genre;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0 ;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0 ;
    }
}
