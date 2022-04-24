package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
import com.lamine.InventoryManagement.model.CommandeFornisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
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
}
