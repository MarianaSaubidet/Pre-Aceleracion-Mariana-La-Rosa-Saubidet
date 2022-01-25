package com.prealkemy.disney.repository.specification;

import com.prealkemy.disney.dto.CharacterDTOFilter;
import com.prealkemy.disney.entity.CharacterEntity;
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
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterDTOFilter filterDTO) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //CREA UNA QUERY DINAMICA.  (hasLength() CHEQUEA SI EXISTE)
            if (StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filterDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filterDTO.getAge())
                );
            }

            if (!CollectionUtils.isEmpty(filterDTO.getMovies())) {
                Join<CharacterEntity, MovieEntity> join = root.join("characterMovies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filterDTO.getMovies()));
            }

            //ELIMINA DUPLICADOS
            query.distinct(true);

            //ORDENA DE MANERA ASCENDENTE
            String orderByField = "name";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            //

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}