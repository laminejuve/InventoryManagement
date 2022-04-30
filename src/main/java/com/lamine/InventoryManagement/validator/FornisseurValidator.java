package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.FornisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FornisseurValidator {

    public static List<String> validate (FornisseurDto fornisseurDto){
        List<String> errors = new ArrayList<>();

        if (fornisseurDto == null){
            errors.add("veuillez rensigner le nom du fornisseur ");
            errors.add("veuillez rensigner le prenom du fornisseur  ");
            errors.add("veuillez rensigner une adresse pour le fornisseur  ");
            errors.add("veuillez rensigner une adresse mail pour le fornisseur");
            return errors;
        }

        if (!StringUtils.hasLength(fornisseurDto.getNom())){
            errors.add("veuillez rensigner le nom du fornisseur ");
        }
        if (!StringUtils.hasLength(fornisseurDto.getPrenom())){
            errors.add("veuillez rensigner le prenom du fornisseur  ");
        }
        if (fornisseurDto.getAdresse() == null){
            errors.add("veuillez rensigner une adresse pour le fornisseur  ");
        }else {
            if (!StringUtils.hasLength(fornisseurDto.getAdresse().getAdresse1())){
                errors.add("le champs 'Adresse 1 ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(fornisseurDto.getAdresse().getCodePostale())){
                errors.add("le champs 'Code postale ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(fornisseurDto.getAdresse().getVille())){
                errors.add("le champs 'Ville ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(fornisseurDto.getAdresse().getPays())){
                errors.add("le champs 'Pays ' est obligatoire  ");
            }
        }
        if (!StringUtils.hasLength(fornisseurDto.getMail())){
            errors.add("veuillez rensigner une adresse mail pour le fornisseur ");
        }

        return errors ;
    }
}
