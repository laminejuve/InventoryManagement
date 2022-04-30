package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.CommandeFornisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFornisseurValidator {

    public static List<String> validate (CommandeFornisseurDto commandeFornisseurDto){
        List<String> errors = new ArrayList<>();

        if (commandeFornisseurDto == null){
            errors.add("veuillez rensigner le code commande Fornisseur ");
            errors.add("veuillez rensigner le Fornisseur pour cette commande  ");
            errors.add("veuillez rensigner lune ligne commande pour cette commande  ");
            return errors;
        }

        if (!StringUtils.hasLength(commandeFornisseurDto.getCode())){
            errors.add("veuillez rensigner le code commande Fornisseur ");
        }
        if (commandeFornisseurDto.getFornisseur() == null){
            errors.add("veuillez rensigner le Fornisseur pour cette commande  ");
        }
        if (commandeFornisseurDto.getLigneCommandeFornisseurs() == null){
            errors.add("veuillez rensigner lune ligne commande pour cette commande  ");
        }

        return errors ;
    }
}
