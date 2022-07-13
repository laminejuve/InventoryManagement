package com.lamine.InventoryManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lamine.InventoryManagement.model.CommandeFornisseur;
import com.lamine.InventoryManagement.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFornisseurDto {

    private Integer id ;
    private String code;
    private Instant dateCommandeFornisseur ;
    private FornisseurDto fornisseur;
    private EtatCommande etatCommande ;
    @JsonIgnore
    private List<LigneCommandeFornisseurDto> ligneCommandeFornisseurs ;
    private Integer idEntreprise;

    public static CommandeFornisseurDto fromEntity(CommandeFornisseur commandeFornisseur){

        if (commandeFornisseur == null){
            // TODO an exception
            return null ;
        }
        return  CommandeFornisseurDto.builder()
                .id(commandeFornisseur.getId())
                .code(commandeFornisseur.getCode())
                .dateCommandeFornisseur(commandeFornisseur.getDateCommandeFornisseur())
                .etatCommande(commandeFornisseur.getEtatCommande())
                .idEntreprise(commandeFornisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFornisseur toEntity(CommandeFornisseurDto commandeFornisseurDto){
        if (commandeFornisseurDto == null){
            //TODO an exception
            return null;
        }
        CommandeFornisseur commandeFornisseur = new CommandeFornisseur();
        commandeFornisseur.setId(commandeFornisseurDto.getId());
        commandeFornisseur.setCode(commandeFornisseurDto.getCode());
        commandeFornisseur.setDateCommandeFornisseur(commandeFornisseurDto.getDateCommandeFornisseur());
        commandeFornisseur.setEtatCommande(commandeFornisseurDto.getEtatCommande());
        commandeFornisseur.setIdEntreprise(commandeFornisseurDto.getIdEntreprise());
        return commandeFornisseur;
    }
    public boolean isCommandeLiveree (){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }

}
