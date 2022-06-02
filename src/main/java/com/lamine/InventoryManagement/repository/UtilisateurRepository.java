package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository <Utilisateur,Integer> {

    Optional<Utilisateur> findByNom (String name);

    Optional<Utilisateur> findByMail(String email);
}
