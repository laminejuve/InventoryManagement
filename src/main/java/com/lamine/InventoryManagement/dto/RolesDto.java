package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Roles;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RolesDto {

    private Integer id ;
    private String roleName;
    private UserDto user ;

    public RolesDto fromEntity (Roles roles){
        if (roles == null){
            //TODO an exception
            return null ;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public Roles toEntity (RolesDto rolesDto){
        if (rolesDto == null){
            //TODO an exception
            return null;
        }
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        return roles;
    }
}
