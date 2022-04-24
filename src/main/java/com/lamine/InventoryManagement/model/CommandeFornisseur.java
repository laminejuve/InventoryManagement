package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class CommandeFornisseur extends AbstractEntity{

    private String code;
    private Instant dateCommandeFornisseur ;
    @ManyToOne
    @JoinColumn(name = "idFornisseur")
    private Fornisseur fornisseur;
    @OneToMany (mappedBy = "commandeFornisseur")
    private List<LigneCommandeFornisseur> ligneCommandeFornisseurs ;

}
