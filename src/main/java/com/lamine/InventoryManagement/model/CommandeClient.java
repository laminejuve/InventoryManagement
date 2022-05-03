package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class CommandeClient extends AbstractEntity {

    private String code;
    private Instant dateCommande ;
    @ManyToOne
    @JoinColumn (name ="idClient")
    private Client client ;
    private Integer idEntreprise;
    @OneToMany (mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients ;

}
