package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository <Integer , Article> {

}

