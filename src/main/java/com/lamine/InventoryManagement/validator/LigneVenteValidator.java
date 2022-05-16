package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate (LigneVenteDto ligneVenteDto){
        List<String> errors = new ArrayList<>();

        if (ligneVenteDto == null){
            errors.add("veuillez rensigner le vente ");
            errors.add("veuillez rensigner une quantité  ");
            errors.add("veuillez rensigner le prix unitaire  ");
            return errors;
        }

        if (ligneVenteDto.getVente()==null){
            errors.add("veuillez rensigner vente ");
        }

        if (ligneVenteDto.getArticle()==null){
            errors.add("veuillez rensigner an article for this vente ");
        }
        if (ligneVenteDto.getQuantity() == null){
            errors.add("veuillez rensigner une quantité  ");
        }
        if (ligneVenteDto.getPrixUnitaire() == null){
            errors.add("veuillez rensigner le prix unitaire  ");
        }

        return errors ;
    }
}
