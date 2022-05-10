package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Utilisateur extends AbstractEntity {

    private String nom;
    private String prenom ;
    private Date dateNaissance;
    private String motDePasse ;
    @Embedded
    private Adresse adresse ;
    private String photo;
    private String mail ;
    private String numTel ;


    @OneToMany (mappedBy = "user")
    private List<Roles> roles ;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;
}
