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
public class Client extends AbstractEntity{

    private String name;
    private String prenom ;
    @Embedded
    private Adresse adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private Integer idEntreprise;

    @OneToMany (mappedBy = "client")
    private List<CommandeClient> commandeClients ;

}
