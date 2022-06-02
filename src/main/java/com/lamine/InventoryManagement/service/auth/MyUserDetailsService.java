package com.lamine.InventoryManagement.service.auth;

import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Utilisateur;
import com.lamine.InventoryManagement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur user = utilisateurRepository.findByMail(email)
                 .orElseThrow(()-> new EntityNotFoundException("**not found**", ErrorCode.UTILISATEUR_NOT_FOUND));
        return new User(user.getMail(),user.getMotDePasse(), Collections.emptyList());

    }
}
