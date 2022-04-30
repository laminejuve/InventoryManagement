package com.lamine.InventoryManagement.validator;


import com.lamine.InventoryManagement.dto.CategoryDto;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate (CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();

        if (categoryDto == null){
            errors.add("veuillez rensigner le code du category ");
            errors.add("veuillez rensigner la designation du category  ");
            return errors;
        }

        if (!StringUtils.hasLength(categoryDto.getCode())){
            errors.add("veuillez rensigner le code du category ");
        }
        if (!StringUtils.hasLength(categoryDto.getDesignation())){
            errors.add("veuillez rensigner la designation du category  ");
        }


        return errors ;
    }
}
