package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class LigneVenteDto {

    private Integer id ;
    private VenteDto vente;
    private BigDecimal quantity ;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;
    private Article article;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente == null){
            //TODO an exception
            return null ;
        }
        return  LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantity(ligneVente.getQuantity())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if (ligneVenteDto == null){
            //TODO an exception
            return null ;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantity(ligneVenteDto.getQuantity());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        return ligneVente ;
    }
}
