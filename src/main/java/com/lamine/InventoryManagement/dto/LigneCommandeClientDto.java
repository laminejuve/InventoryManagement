package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.LigneCommandeClient;
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

    public LigneCommandeClientDto fromEntity (LigneCommandeClient ligneCommandeClient){
        if (ligneCommandeClient == null ){
            // TODO an exception
            return null ;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantity(ligneCommandeClient.getQuantity())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }
    public LigneCommandeClient toEntity (LigneCommandeClientDto ligneCommandeClientDto){
        if(ligneCommandeClientDto == null ){
            //TODO an exception
            return null ;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantity(ligneCommandeClientDto.getQuantity());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        return ligneCommandeClient ;
    }
}
