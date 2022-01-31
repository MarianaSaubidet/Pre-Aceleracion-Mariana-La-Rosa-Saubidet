package com.prealkemy.disney.auth.service;

import com.prealkemy.disney.auth.dto.UserDTO;
import com.prealkemy.disney.auth.entity.UserEntity;
import com.prealkemy.disney.auth.repository.UserRepository;
import com.prealkemy.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public UserDetailsCustomService() {
    }

    @Override
    //BUSCA POR NOMBRE DE USUARIO
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(username == null) {
            throw new UsernameNotFoundException("Username or password not found.");
        }

        //COLLECTIONS.EMPTYLIST SON LOS ROLES. PERO NO LOS VAMOS A MANEJAR. POR ESO ESTA VACIA
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);

        if(userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}
