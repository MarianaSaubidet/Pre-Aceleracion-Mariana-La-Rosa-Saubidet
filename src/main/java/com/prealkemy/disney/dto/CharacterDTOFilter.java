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
public class CharacterDTOFilter {
    private String name;
    private Integer age;
    private Set<Long> movies;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0 ;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0 ;
    }
}
