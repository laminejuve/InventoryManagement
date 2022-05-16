package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LigneVente extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;
    @ManyToOne
    @JoinColumn (name = "idArticle")
    private Article article;

    private BigDecimal quantity ;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

}
