package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate (LigneCommandeClientDto ligneCommandeClientDto){
        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null){
            errors.add("veuillez rensigner un article ");
            errors.add("veuillez rensigner une commande ");
            errors.add("veuillez rensigner une quantité  ");
            errors.add("veuillez rensigner le prix unitaire  ");
            return errors;
        }

        if (ligneCommandeClientDto.getArticle()== null){
            errors.add("veuillez rensigner un article ");
        }
        if (ligneCommandeClientDto.getCommandeClient()==null){
            errors.add("veuillez rensigner une commande ");
        }
        if (ligneCommandeClientDto.getQuantity() == null){
            errors.add("veuillez rensigner une quantité  ");
        }
        if (ligneCommandeClientDto.getPrixUnitaire() == null){
            errors.add("veuillez rensigner le prix unitaire  ");
        }

        return errors ;
    }
}
