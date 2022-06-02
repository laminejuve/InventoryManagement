package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category,Integer> {


}
