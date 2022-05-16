package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.ArticleDto;
import com.lamine.InventoryManagement.dto.LigneVenteDto;
import com.lamine.InventoryManagement.dto.VenteDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class VenteValidator {

    public static List<String> validate (VenteDto venteDto){
        List<String> errors = new ArrayList<>();

        if (venteDto == null){
            errors.add("vente is null");
            return errors;
        }

        if (!(StringUtils.hasLength(venteDto.getCode()))){
            errors.add("you have to enter a code ");
        }

        if (venteDto.getLigneVenteDtos().isEmpty()){
            errors.add("you have to enter at least one ligne vente ");
        }
        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            List<String> ligneVenteErrors = LigneVenteValidator.validate(ligneVenteDto);
            if (!ligneVenteErrors.isEmpty()){
                errors.add("ligne vente is not valid");
            }
            List<String> articleErrors = ArticleValidator.validate(ArticleDto.fromEntity(ligneVenteDto.getArticle()));
            if (!articleErrors.isEmpty()){
                errors.add("aticle is not valid");
            }

        });

        return errors ;
    }
}
