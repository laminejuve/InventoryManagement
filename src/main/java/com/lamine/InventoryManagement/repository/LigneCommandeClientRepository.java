package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository <LigneCommandeClient ,Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId (Integer idCommandeClient);
}
