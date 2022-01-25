package com.prealkemy.disney.repository.specification;

import com.prealkemy.disney.dto.MovieDTOFilter;
import com.prealkemy.disney.entity.GenreEntity;
import com.prealkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getFiltered(MovieDTOFilter movieDTOFilter) {
        //
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //CREA UNA QUERY DINAMICA.  (hasLength() CHEQUEA SI EXISTE)
            if (StringUtils.hasLength(movieDTOFilter.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieDTOFilter.getTitle().toLowerCase() + "%"
                        )
                );
            }

            //GENERO
            if (!CollectionUtils.isEmpty(movieDTOFilter.getGenre())) {
                Join<MovieEntity, GenreEntity> join = root.join("movieGenres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(movieDTOFilter.getGenre()));
            }
            query.distinct(true);

            //ORDENA DE MANERA ASCENDENTE
            String orderByField = "title";
            query.orderBy(
                    movieDTOFilter.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            //RETURN
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

