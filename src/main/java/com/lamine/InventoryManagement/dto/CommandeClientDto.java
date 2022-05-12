package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.CommandeClient;
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
    private Integer idEntreprise;

    public static CommandeClientDto  fromEntity (CommandeClient commandeClient){

        if (commandeClient == null){
            // TODO an exception
            return null ;
        }
        return  CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();
    }

    public static CommandeClient toEntity (CommandeClientDto commandeClientDto){
        if (commandeClientDto == null){
            //TODO an exception
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        return commandeClient;
    }
}
