package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.LigneCommandeFornisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFornisseurValidator {

    public static List<String> validate (LigneCommandeFornisseurDto ligneCommandeFornisseurDto){
        List<String> errors = new ArrayList<>();

        if (ligneCommandeFornisseurDto == null){
            errors.add("veuillez rensigner un article ");
            errors.add("veuillez rensigner une commande ");
            errors.add("veuillez rensigner une quantité  ");
            errors.add("veuillez rensigner le prix unitaire  ");
            return errors;
        }

        if (ligneCommandeFornisseurDto.getArticle()== null){
            errors.add("veuillez rensigner un article ");
        }
        if (ligneCommandeFornisseurDto.getCommandeFornisseur()==null){
            errors.add("veuillez rensigner une commande ");
        }
        if (ligneCommandeFornisseurDto.getQuantity() == null){
            errors.add("veuillez rensigner une quantité  ");
        }
        if (ligneCommandeFornisseurDto.getPrixUnitaire() == null){
            errors.add("veuillez rensigner le prix unitaire  ");
        }

        return errors ;
    }
}
