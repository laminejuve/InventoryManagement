package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.LigneVenteDto;
import com.lamine.InventoryManagement.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate (VenteDto venteDto){
        List<String> errors = new ArrayList<>();

        if (venteDto == null){
            errors.add("veuillez rensigner un code");
            return errors;
        }

        if (!(StringUtils.hasLength(venteDto.getCode()))){
            errors.add("veuillez rensigner un code ");
        }

        return errors ;
    }
}
