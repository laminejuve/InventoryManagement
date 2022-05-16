package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Vente extends AbstractEntity {

    private String code;
    private Instant dateVente;
    private String commentaire;
    private Integer idEntreprise;

    @OneToMany (mappedBy = "vente" ,fetch = FetchType.LAZY)
    List<LigneVente> ligneVentes ;
}
