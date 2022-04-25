package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.MvmStock;
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

    public MvmStockDto fromEntity (MvmStock mvmStock){
        if (mvmStock == null){
            // TODO an exception
            return null ;
        }
        return MvmStockDto.builder()
                .id(mvmStock.getId())
                .dateMvt(mvmStock.getDateMvt())
                .quantity(mvmStock.getQuantity())
                .build();
    }
    public MvmStock toEntity (MvmStockDto mvmStockDto){
        if (mvmStockDto ==  null ){
            //TODO an exception
            return null ;
        }
        MvmStock mvmStock = new MvmStock();
        mvmStock.setId(mvmStockDto.getId());
        mvmStock.setDateMvt(mvmStockDto.getDateMvt());
        mvmStock.setQuantity(mvmStockDto.getQuantity());
        return mvmStock ;
    }
}

