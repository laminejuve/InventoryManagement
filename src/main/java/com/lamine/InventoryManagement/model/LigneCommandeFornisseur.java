package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LigneCommandeFornisseur extends AbstractEntity {
    @ManyToOne
    @JoinColumn (name = "idArticle")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "idCommandeFornisseur")
    private CommandeFornisseur commandeFornisseur;

    private BigDecimal quantity;
    private BigDecimal prixUitaire ;
    private Integer idEntreprise;



}
