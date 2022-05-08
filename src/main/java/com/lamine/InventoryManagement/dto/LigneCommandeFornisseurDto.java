package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.LigneCommandeFornisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeFornisseurDto {

    private Integer id ;
    private ArticleDto article;
    private CommandeFornisseurDto commandeFornisseur;
    private BigDecimal quantity;
    private BigDecimal prixUnitaire ;

    public LigneCommandeFornisseurDto fromEntity (LigneCommandeFornisseur ligneCommandeFornisseur){
        if (ligneCommandeFornisseur == null ){
            // TODO an exception
            return null ;
        }
        return LigneCommandeFornisseurDto.builder()
                .id(ligneCommandeFornisseur.getId())
                .quantity(ligneCommandeFornisseur.getQuantity())
                .prixUnitaire(ligneCommandeFornisseur.getPrixUitaire())
                .build();
    }
    public LigneCommandeFornisseur toEntity (LigneCommandeFornisseurDto ligneCommandeFornisseurDto){
        if(ligneCommandeFornisseurDto == null ){
            //TODO an exception
            return null ;
        }
        LigneCommandeFornisseur ligneCommandeFornisseur = new LigneCommandeFornisseur();
        ligneCommandeFornisseur.setId(ligneCommandeFornisseurDto.getId());
        ligneCommandeFornisseur.setQuantity(ligneCommandeFornisseurDto.getQuantity());
        ligneCommandeFornisseur.setPrixUitaire(ligneCommandeFornisseurDto.getPrixUnitaire());
        return ligneCommandeFornisseur ;
    }

}
