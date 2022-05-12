package com.lamine.InventoryManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer idEntreprise;

    public static ArticleDto fromEntity (Article article){
        if (article == null){
            // exception
            return null;
        }
        return   ArticleDto.builder()
                .id(article.getId())
                .designation(article.getDesignation())
                .codeArticel(article.getCodeArticle())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                .tauxTva(article.getTauxTva())
                .build();
    }

    public static Article toEntity (ArticleDto articleDto){
        if (articleDto == null){
            // todo
            return null;
        }
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticel());
        article.setDesignation(articleDto.getDesignation());
        article.setPhoto(articleDto.getPhoto());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setTauxTva(articleDto.getTauxTva());
        article.setIdEntreprise(articleDto.getIdEntreprise());

        return article;
    }
}
