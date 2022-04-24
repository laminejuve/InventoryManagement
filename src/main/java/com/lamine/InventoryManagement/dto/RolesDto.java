package com.lamine.InventoryManagement.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RolesDto {

    private Integer id ;
    private String roleName;
    private UserDto user ;
}
