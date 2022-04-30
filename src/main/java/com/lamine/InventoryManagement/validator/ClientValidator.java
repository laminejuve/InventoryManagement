package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate (ClientDto clientDto){
        List<String> errors = new ArrayList<>();

        if (clientDto == null){
            errors.add("veuillez rensigner le nom du client ");
            errors.add("veuillez rensigner le prenom du client  ");
            errors.add("veuillez rensigner une adresse pour le client  ");
            errors.add("veuillez rensigner une adresse mail pour le client");
            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getName())){
            errors.add("veuillez rensigner le nom du client ");
        }
        if (!StringUtils.hasLength(clientDto.getPrenom())){
            errors.add("veuillez rensigner le prenom du client  ");
        }
        if (clientDto.getAdresse() == null){
            errors.add("veuillez rensigner une adresse pour le client  ");
        }else {
            if (!StringUtils.hasLength(clientDto.getAdresse().getAdresse1())){
                errors.add("le champs 'Adresse 1 ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(clientDto.getAdresse().getCodePostale())){
                errors.add("le champs 'Code postale ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(clientDto.getAdresse().getVille())){
                errors.add("le champs 'Ville ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(clientDto.getAdresse().getPays())){
                errors.add("le champs 'Pays ' est obligatoire  ");
            }
        }
        if (!StringUtils.hasLength(clientDto.getMail())){
            errors.add("veuillez rensigner une adresse mail pour le client ");
        }

        return errors ;
    }
}
