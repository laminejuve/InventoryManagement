package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.model.CommandeFornisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFornisseurDto {

    private Integer id ;
    private ArticleDto article;
    private CommandeFornisseurDto commandeFornisseur;
    private BigDecimal quantity;
    private BigDecimal prixUitaire ;

}
