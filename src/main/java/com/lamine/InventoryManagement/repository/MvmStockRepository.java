package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.MvmStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvmStockRepository extends JpaRepository <Integer , MvmStock> {

}
