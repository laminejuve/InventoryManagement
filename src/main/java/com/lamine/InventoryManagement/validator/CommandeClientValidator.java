package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate (CommandeClientDto commandeClientDto){
        List<String> errors = new ArrayList<>();

        if (commandeClientDto == null){
            errors.add("veuillez rensigner le code commande client ");
            errors.add("veuillez rensigner le client pour cette commande  ");
            errors.add("veuillez rensigner lune ligne commande pour cette commande  ");
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCode())){
            errors.add("veuillez rensigner le code commande client ");
        }
        if (commandeClientDto.getClient() == null){
            errors.add("veuillez rensigner le client pour cette commande  ");
        }
        if (commandeClientDto.getLigneCommandeClients() == null){
            errors.add("veuillez rensigner lune ligne commande pour cette commande  ");
        }

        return errors ;
    }
}
