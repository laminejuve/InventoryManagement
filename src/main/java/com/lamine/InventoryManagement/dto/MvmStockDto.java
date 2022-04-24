package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class MvmStockDto {

    private Integer id ;
    private Instant dateMvt ;
    private BigDecimal quantity;
    private ArticleDto article;
    private TypeMvtStk typeMvt ;
}

