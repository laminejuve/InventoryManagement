package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate (ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("veuillez rensigner le code d'article ");
            errors.add("veuillez rensigner lla designation article  ");
            errors.add("veuillez rensigner le prix unitaire HT de l'article  ");
            errors.add("veuillez rensigner le prix unitaire TTC de l'article  ");
            errors.add("veuillez rensigner le taux Tva de l'article  ");
            errors.add("veuillez rensigner une categorie pour l'article ");
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticel())){
            errors.add("veuillez rensigner le code d'article ");
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("veuillez rensigner lla designation article  ");
        }
        if (articleDto.getPrixUnitaireHt() == null){
            errors.add("veuillez rensigner le prix unitaire HT de l'article  ");
        }
        if (articleDto.getPrixUnitaireTtc() == null){
            errors.add("veuillez rensigner le prix unitaire TTC de l'article  ");
        }
        if (articleDto.getTauxTva() == null){
            errors.add("veuillez rensigner le taux Tva de l'article  ");
        }
        if (articleDto.getCategory() == null){
            errors.add("veuillez rensigner une categorie pour l'article ");
        }

        return errors ;
    }
}
