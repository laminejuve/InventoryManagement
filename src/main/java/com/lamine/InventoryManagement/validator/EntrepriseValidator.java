package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.EntrepriseDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate (EntrepriseDto entrepriseDto){
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null){
            errors.add("veuillez rensigner le nom de l'entreprise ");
            errors.add("veuillez rensigner le prenom de l'entreprise  ");
            errors.add("veuillez rensigner une adresse pour l'entreprise  ");
            errors.add("veuillez rensigner un code fiscale pour l'entreprise");
            errors.add("veuillez rensigner une adresse mail pour l'entreprise");
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())){
            errors.add("veuillez rensigner le nom de  l'entreprise ");
        }
        if (!StringUtils.hasLength(entrepriseDto.getDescription())){
            errors.add("veuillez rensigner la description de l'entreprise  ");
        }
        if (entrepriseDto.getAdresse() == null){
            errors.add("veuillez rensigner une adresse pour le entreprise  ");
        }else {
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getAdresse1())){
                errors.add("le champs 'Adresse 1 ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getCodePostale())){
                errors.add("le champs 'Code postale ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getVille())){
                errors.add("le champs 'Ville ' est obligatoire  ");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getPays())){
                errors.add("le champs 'Pays ' est obligatoire  ");
            }
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())){
            errors.add("veuillez rensigner un code fiscale pour l'entreprise ");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())){
            errors.add("veuillez rensigner une adresse mail pour l'entreprise ");
        }

        return errors ;
    }
}
