package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.Instant;

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
}
