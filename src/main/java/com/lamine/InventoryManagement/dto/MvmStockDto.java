package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.MvmStock;
import com.lamine.InventoryManagement.model.SourceMvmStk;
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
    private Integer idEntreprise;
    private SourceMvmStk sourceMvmStk;

    public static MvmStockDto fromEntity(MvmStock mvmStock){
        if (mvmStock == null){
            // TODO an exception
            return null ;
        }
        return MvmStockDto.builder()
                .id(mvmStock.getId())
                .dateMvt(mvmStock.getDateMvt())
                .article(ArticleDto.fromEntity(mvmStock.getArticle()))
                .quantity(mvmStock.getQuantity())
                .typeMvt(mvmStock.getTypeMvt())
                .sourceMvmStk(mvmStock.getSourceMvmStk())
                .idEntreprise(mvmStock.getIdEntreprise())
                .build();
    }
    public static MvmStock toEntity (MvmStockDto mvmStockDto){
        if (mvmStockDto ==  null ){
            //TODO an exception
            return null ;
        }
        MvmStock mvmStock = new MvmStock();
        mvmStock.setId(mvmStockDto.getId());
        mvmStock.setDateMvt(mvmStockDto.getDateMvt());
        mvmStock.setQuantity(mvmStockDto.getQuantity());
        mvmStock.setArticle(ArticleDto.toEntity(mvmStockDto.getArticle()));
        mvmStock.setTypeMvt(mvmStockDto.getTypeMvt());
        mvmStock.setSourceMvmStk(mvmStockDto.getSourceMvmStk());
        mvmStock.setIdEntreprise(mvmStockDto.getIdEntreprise());
        return mvmStock ;
    }
}

