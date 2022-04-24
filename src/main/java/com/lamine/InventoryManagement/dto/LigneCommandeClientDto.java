package com.lamine.InventoryManagement.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientDto {

    private Integer id ;
    private ArticleDto article;
    private CommandeClientDto commandeClient;
    private BigDecimal quantity;
    private BigDecimal prixUnitaire;
}
