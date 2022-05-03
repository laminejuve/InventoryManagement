package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository <Integer , Vente> {

}
