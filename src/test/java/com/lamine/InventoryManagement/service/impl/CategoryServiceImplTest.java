package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.CategoryDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void saveCategorySuccess(){

        CategoryDto expectedCategory = CategoryDto.builder()
                                          .code("cat test")
                                          .designation("designation test")
                                          .idEntreprise(2)
                                           .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getDesignation(),savedCategory.getDesignation());
        assertEquals(expectedCategory.getCode(),savedCategory.getCode());
        assertEquals(expectedCategory.getIdEntreprise(),savedCategory.getIdEntreprise());


    }
    @Test
    public void updateCategorySuccess(){

        CategoryDto expectedCategory = CategoryDto.builder()
                .code("cat test")
                .designation("designation test")
                .idEntreprise(2)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);
        
        CategoryDto updatedCategory = savedCategory;
        updatedCategory.setCode("cat update");

        savedCategory = categoryService.save(updatedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(updatedCategory.getDesignation(),savedCategory.getDesignation());
        assertEquals(updatedCategory.getCode(),savedCategory.getCode());
        assertEquals(updatedCategory.getIdEntreprise(),savedCategory.getIdEntreprise());


    }
    @Test
    public void thrwInvalidEntityException(){

        CategoryDto expectedCategory = CategoryDto.builder()
                .build();

        EntityInvalidException entityInvalidException = assertThrows(EntityInvalidException.class, () -> categoryService.save(expectedCategory));
        assertEquals(ErrorCode.CATEGORY_NOT_VALID ,entityInvalidException.getErrorCode());

    }
    @Test
    public void thrwEntityNotFoundException(){


        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> categoryService.findById(32));
        assertEquals(ErrorCode.CATEGORY_NOT_FOUND ,entityNotFoundException.getErrorCode());

    }

}