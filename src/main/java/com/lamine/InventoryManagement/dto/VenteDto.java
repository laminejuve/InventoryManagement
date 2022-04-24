package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VenteDto {

    private Integer id ;
    private String code;
    private Instant dateVente;
    private String commentaire;
}
