package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Roles;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RolesDto {

    private Integer id ;
    private String roleName;
    private UtilisateurDto utilisateurDto ;

    public RolesDto fromEntity (Roles roles){
        if (roles == null){
            // exception
            return null ;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public Roles toEntity (RolesDto rolesDto){
        if (rolesDto == null){
            // exception
            return null;
        }
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        return roles;
    }
}
