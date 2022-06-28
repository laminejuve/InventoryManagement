package com.lamine.InventoryManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lamine.InventoryManagement.model.CommandeClient;
import com.lamine.InventoryManagement.model.EtatCommande;
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

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients ;
    private Integer idEntreprise;

    private EtatCommande etatCommande ;

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
                .etatCommande(commandeClient.getEtatCommande())
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
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
        return commandeClient;
    }

    public boolean isCommandeLiveree (){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
