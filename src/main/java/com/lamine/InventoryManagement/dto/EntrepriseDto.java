package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
import com.lamine.InventoryManagement.model.Entreprise;
import com.lamine.InventoryManagement.model.User;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private Integer id ;
    private String nom ;
    private String description;
    private AdresseDto adresse;
    private String photo;
    private String codeFiscal;
    private String email ;
    private String numTel ;
    private String siteWeb ;
    private List<UserDto> users ;

    public EntrepriseDto fromEntity (Entreprise entreprise){
        if(entreprise == null ){
            // TODO an exception
            return null ;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .photo(entreprise.getPhoto())
                .codeFiscal(entreprise.getCodeFiscal())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .build();
    }

    public Entreprise toEntity (EntrepriseDto entrepriseDto){

        if (entrepriseDto == null ){
            // TODO an exception
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId((entrepriseDto.getId()));
        entreprise.setNom(entreprise.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());

        return entreprise;
    }
}
