package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category,Integer> {

}
