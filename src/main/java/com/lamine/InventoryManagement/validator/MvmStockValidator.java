package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.MvmStockDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvmStockValidator {

    public static List<String> validate (MvmStockDto mvmStockDto){
        List<String> errors = new ArrayList<>();

        if (mvmStockDto == null){
            errors.add("veuillez rensigner une date movement de stock ");
            errors.add("veuillez rensigner une Quantité ");
            errors.add("veuillez rensigner un article ");
            errors.add("veuillez rensigner un type movement ");

            return errors;
        }

        if (mvmStockDto.getDateMvt() == null){
            errors.add("veuillez rensigner une date movement de stock ");
        }
        if (mvmStockDto.getQuantity() == null){
            errors.add("veuillez rensigner une Quantité ");
        }
        if (mvmStockDto.getArticle() == null){
            errors.add("veuillez rensigner un article ");
        }
        if (mvmStockDto.getTypeMvt() == null){
            errors.add("veuillez rensigner un type movement ");
        }

        return errors ;
    }
}
