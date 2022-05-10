package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate (UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null){
            errors.add("veuillez rensigner le nom d'utilisateur ");
            errors.add("veuillez rensigner le prenom d'utilisateur  ");
            errors.add("veuillez rensigner la date de naissance d'utilisateur  ");
            errors.add("veuillez rensigner un mot de passe pour l'utilisateur  ");
            errors.add("veuillez rensigner une adresse pour l'utilisateur  ");
            errors.add("veuillez rensigner une adresse mail pour l'utilisateur ");
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("veuillez rensigner le nom d'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("veuillez rensigner le prenom d'utilisateur  ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getDateNaissance().toString())){
            errors.add("veuillez rensigner la date de naissance d'utilisateur  ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("veuillez rensigner un mot de passe pour l'utilisateur  ");
        }
        if (utilisateurDto.getAdresse() == null){
            errors.add("veuillez rensigner une adresse pour l'utilisateur  ");
        }else {
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("le champs 'Adresse 1 ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("le champs 'Code postale ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("le champs 'Ville ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("le champs 'Pays ' est obligatoire  ");
            }
        }
        if (!StringUtils.hasLength(utilisateurDto.getMail())){
            errors.add("veuillez rensigner une adresse mail pour l'utilisateur ");
        }

        return errors ;
    }
}
