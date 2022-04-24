package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Fornisseur;
import com.lamine.InventoryManagement.model.LigneCommandeFornisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeFornisseurDto {

    private Integer id ;
    private String code;
    private Instant dateCommandeFornisseur ;
    private FornisseurDto fornisseur;
    private List<LigneCommandeFornisseurDto> ligneCommandeFornisseurs ;
}
