package com.lamine.InventoryManagement.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDto {

    private Integer id ;
    private String code;
    private Instant dateCommande ;
    private ClientDto client ;
    private List<LigneCommandeClientDto> ligneCommandeClients ;
}
