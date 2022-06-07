package com.lamine.InventoryManagement.service.auth;

import com.lamine.InventoryManagement.dto.UtilisateurDto;
import com.lamine.InventoryManagement.model.auth.ExtendeUser;
import com.lamine.InventoryManagement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UtilisateurDto user = utilisateurService.getUtilisateurByEmail(email);

        System.out.println(user.getMail() +" *** "+user.getMotDePasse());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

      //  user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        System.out.println(authorities);


        return new ExtendeUser (user.getMail(), user.getMotDePasse(), Collections.emptyList());

    }
}
