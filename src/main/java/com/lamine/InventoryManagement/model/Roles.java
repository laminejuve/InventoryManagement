package com.lamine.InventoryManagement.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Roles extends AbstractEntity {

    private String roleName;
    @ManyToOne
    @JoinColumn (name = "idUser")
    private User user ;
}
