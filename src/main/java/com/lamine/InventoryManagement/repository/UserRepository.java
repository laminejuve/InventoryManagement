package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <Integer , User> {

}
