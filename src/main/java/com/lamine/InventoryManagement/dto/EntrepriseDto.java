package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
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
}
