package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate (UserDto userDto){
        List<String> errors = new ArrayList<>();

        if (userDto == null){
            errors.add("veuillez rensigner le nom d'utilisateur ");
            errors.add("veuillez rensigner le prenom d'utilisateur  ");
            errors.add("veuillez rensigner la date de naissance d'utilisateur  ");
            errors.add("veuillez rensigner un mot de passe pour l'utilisateur  ");
            errors.add("veuillez rensigner une adresse pour l'utilisateur  ");
            errors.add("veuillez rensigner une adresse mail pour l'utilisateur ");
        }

        if (!StringUtils.hasLength(userDto.getNom())){
            errors.add("veuillez rensigner le nom d'utilisateur ");
        }
        if (!StringUtils.hasLength(userDto.getPrenom())){
            errors.add("veuillez rensigner le prenom d'utilisateur  ");
        }
        if (!StringUtils.hasLength(userDto.getDateNaissance().toString())){
            errors.add("veuillez rensigner la date de naissance d'utilisateur  ");
        }
        if (!StringUtils.hasLength(userDto.getMotDePasse())){
            errors.add("veuillez rensigner un mot de passe pour l'utilisateur  ");
        }
        if (userDto.getAdresse() == null){
            errors.add("veuillez rensigner une adresse pour l'utilisateur  ");
        }else {
            if (!StringUtils.hasLength(userDto.getAdresse().getAdresse1())){
                errors.add("le champs 'Adresse 1 ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(userDto.getAdresse().getCodePostale())){
                errors.add("le champs 'Code postale ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(userDto.getAdresse().getVille())){
                errors.add("le champs 'Ville ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(userDto.getAdresse().getPays())){
                errors.add("le champs 'Pays ' est obligatoire  ");
            }
        }
        if (!StringUtils.hasLength(userDto.getMail())){
            errors.add("veuillez rensigner une adresse mail pour l'utilisateur ");
        }

        return errors ;
    }
}
