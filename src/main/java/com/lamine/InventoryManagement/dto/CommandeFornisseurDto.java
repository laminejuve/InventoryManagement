package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.CommandeFornisseur;
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
    private List<LigneCommandeFornisseurDto> ligneCommandeFornisseurs ;
    private Integer idEntreprise;

    public CommandeFornisseurDto fromEntity (CommandeFornisseur commandeFornisseur){

        if (commandeFornisseur == null){
            // TODO an exception
            return null ;
        }
        return  CommandeFornisseurDto.builder()
                .id(commandeFornisseur.getId())
                .code(commandeFornisseur.getCode())
                .dateCommandeFornisseur(commandeFornisseur.getDateCommandeFornisseur())
                .idEntreprise(commandeFornisseur.getIdEntreprise())
                .build();
    }

    public CommandeFornisseur toEntity (CommandeFornisseurDto commandeFornisseurDto){
        if (commandeFornisseurDto == null){
            //TODO an exception
            return null;
        }
        CommandeFornisseur commandeFornisseur = new CommandeFornisseur();
        commandeFornisseur.setId(commandeFornisseurDto.getId());
        commandeFornisseur.setCode(commandeFornisseurDto.getCode());
        commandeFornisseur.setDateCommandeFornisseur(commandeFornisseurDto.getDateCommandeFornisseur());
        commandeFornisseur.setIdEntreprise(commandeFornisseurDto.getIdEntreprise());
        return commandeFornisseur;
    }
}
