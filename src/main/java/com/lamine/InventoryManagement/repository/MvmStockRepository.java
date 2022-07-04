package com.lamine.InventoryManagement.repository;

import com.lamine.InventoryManagement.model.MvmStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvmStockRepository extends JpaRepository <MvmStock ,Integer> {

    @Query("select sum(m.quantity) from MvmStock m where m.article.id = :idArticle ")
    BigDecimal stockReelArticle(@Param("idArticle") Integer idArticle);
    List<MvmStock> findAllByArticleId(Integer idArticle);
}
