package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id ;
    private String name;
    private String prenom ;
    private Adresse adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private List<CommandeClientDto> commandeClients ;
}
