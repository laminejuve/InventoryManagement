package com.lamine.InventoryManagement.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangePasswordUtilisateurDto {

    private Integer id;
    private  String password ;
    private String confirmPassword ;
}
