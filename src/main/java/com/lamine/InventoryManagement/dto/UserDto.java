package com.lamine.InventoryManagement.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class UserDto {

    private Integer id ;
    private String nom;
    private String prenom ;
    private Date dateNaissance;
    private String motDePasse ;
    private AdresseDto adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private List<RolesDto> roles ;
    private EntrepriseDto entreprise;
}
