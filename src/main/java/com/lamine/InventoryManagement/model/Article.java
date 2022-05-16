package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (callSuper = true)
@Entity
public class Article extends  AbstractEntity{

    private String codeArticle;
    private String designation ;
    private BigDecimal prixUnitaireHt ;
    private BigDecimal tauxTva ;
    private BigDecimal prixUnitaireTtc;
    private String photo ;
    private Integer idEntreprise;
    @ManyToOne
    @JoinColumn (name = "idCategory")
    private Category category ;



}
