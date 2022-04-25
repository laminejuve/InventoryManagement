package com.lamine.InventoryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class MvmStock extends AbstractEntity {

    private Instant dateMvt ;
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

   // private TypeMvtStk typeMvt ;
}
