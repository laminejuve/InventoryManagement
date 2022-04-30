package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate (RolesDto rolesDto){
        List<String> errors = new ArrayList<>();

        if (rolesDto == null){
            errors.add("veuillez rensigner le nom du role ");
            return errors;
        }

        if (!(StringUtils.hasLength(rolesDto.getRoleName()))){
            errors.add("veuillez rensigner le nom du role ");
        }


        return errors ;
    }
}
