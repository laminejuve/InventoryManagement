package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Address;

@Builder
@Data
public class AdresseDto {

    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;

    public AdresseDto fromEntity (Adresse adresse){
        if (adresse == null){
            // exception
            return null;
        }
        return   AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostale(adresse.getCodePostale())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public Adresse toEntity (AdresseDto adresseDto){
        if (adresseDto == null){
            // exception
            return null;
        }
        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostale(adresseDto.getCodePostale());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());

        return  adresse;
    }
}
