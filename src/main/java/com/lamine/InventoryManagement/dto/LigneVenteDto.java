package com.lamine.InventoryManagement.dto;

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

    public LigneVenteDto fromEntity (LigneVente ligneVente){
        if (ligneVente == null){
            //TODO an exception
            return null ;
        }
        return  LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantity(ligneVente.getQuantity())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }

    public LigneVente toEntity (LigneVenteDto ligneVenteDto){
        if (ligneVenteDto == null){
            //TODO an exception
            return null ;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantity(ligneVenteDto.getQuantity());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        return ligneVente ;
    }
}
