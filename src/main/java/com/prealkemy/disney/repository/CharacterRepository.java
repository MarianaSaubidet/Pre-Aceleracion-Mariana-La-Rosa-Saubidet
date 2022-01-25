package com.prealkemy.disney.repository;

import com.prealkemy.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    List<CharacterEntity> findAll(Specification<CharacterEntity>entitySpecification);

}
