package com.lamine.InventoryManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lamine.InventoryManagement.model.Adresse;
import com.lamine.InventoryManagement.model.Article;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {
    private Integer id ;
    private String codeArticel;
    private String designation ;
    private BigDecimal prixUnitaireHt ;
    private BigDecimal tauxTva ;
    private BigDecimal prixUnitaireTtc;
    private String photo ;
    @JsonIgnore
    private CategoryDto category ;

    public ArticleDto fromEntity (Article article){
        if (article == null){
            // exception
            return null;
        }
        return   ArticleDto.builder()
                .id(article.getId())
                .designation(article.getDesignation())
                .codeArticel(article.getCodeArticel())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .tauxTva(article.getTauxTva())
                .build();
    }
}
