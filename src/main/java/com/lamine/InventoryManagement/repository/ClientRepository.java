package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Integer , Client> {

}
