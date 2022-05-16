package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VenteDto {

    private Integer id ;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private Integer idEntreprise;
    List<LigneVenteDto> ligneVenteDtos ;

    public static VenteDto fromEntity(Vente vente){
        if (vente == null){
            //TODO an exception
            return null ;
        }
        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaire(vente.getCommentaire())
                .idEntreprise(vente.getIdEntreprise())
                .build();
    }
    public static Vente toEntity(VenteDto venteDto){
        if (venteDto == null){
            //TODO an exception
            return null;
        }
        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaire(venteDto.getCommentaire());
        vente.setIdEntreprise(venteDto.getIdEntreprise());
        return vente ;
    }
}
