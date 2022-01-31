package com.prealkemy.disney.auth.repository;

import com.prealkemy.disney.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long > {
    //COLUMN USERNAME - DEVUELVE USERENTITY
    UserEntity findByUsername(String username);

}
