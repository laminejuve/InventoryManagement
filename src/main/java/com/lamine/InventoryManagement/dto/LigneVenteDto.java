package com.lamine.InventoryManagement.dto;

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
}
