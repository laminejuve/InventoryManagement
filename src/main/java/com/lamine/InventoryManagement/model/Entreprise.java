package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Entreprise extends AbstractEntity{

    private String nom ;
    private String description;
    @Embedded
    private Adresse adresse;
    private String photo;
    private String codeFiscal;
    private String email ;
    private String numTel ;
    private String siteWeb ;
    @OneToMany (mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs ;

}
