package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.LigneCommandeFornisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LigneCommandeFornisseurRepository extends JpaRepository <LigneCommandeFornisseur ,Integer> {

    List<LigneCommandeFornisseur> findAllByCommandeFornisseurId(Integer idCommandeFornisseur);
}
