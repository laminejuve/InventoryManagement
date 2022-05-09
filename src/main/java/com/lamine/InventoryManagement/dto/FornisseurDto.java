package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Fornisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FornisseurDto {

    private Integer id ;
    private String nom;
    private String prenom ;
    private AdresseDto adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private List<CommandeFornisseurDto> commandeFornisseurs;

    public static FornisseurDto fromEntity(Fornisseur fornisseur){

        if (fornisseur == null){
            // do an exception
            return null;
        }
        return  FornisseurDto.builder()
                .id(fornisseur.getId())
                .nom(fornisseur.getNom())
                .prenom(fornisseur.getPrenom())
                .mail(fornisseur.getMail())
                .numTel(fornisseur.getNumTel())
                .photo(fornisseur.getPhoto())
                .build();
    }
    public static Fornisseur toEntity(FornisseurDto fornisseurDto){

        if (fornisseurDto == null){
            // to do an exception
            return null;
        }
        Fornisseur fornisseur = new Fornisseur();
        fornisseur.setId(fornisseurDto.getId()) ;
        fornisseur.setNom(fornisseurDto.getNom());
        fornisseur.setPrenom(fornisseurDto.getPrenom());
        fornisseur.setMail(fornisseurDto.getMail());
        fornisseur.setPhoto(fornisseurDto.getPhoto());
        fornisseur.setNumTel(fornisseurDto.getNumTel());
        return fornisseur;
    }
}
