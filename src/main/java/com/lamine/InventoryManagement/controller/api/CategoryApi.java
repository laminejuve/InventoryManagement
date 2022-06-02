package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.CategoryDto;
import com.lamine.InventoryManagement.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping (value = APP_ROOT+"/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody  CategoryDto categoryDto);

    @GetMapping (value = APP_ROOT+"/categories/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById (@PathVariable Integer id);

   /* @GetMapping (value = APP_ROOT+"/categories/{code}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode (@PathVariable String code);*/

    @GetMapping (value = APP_ROOT+"/categories/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll ();

    @DeleteMapping (value = APP_ROOT+"/categories/delete/{id}")
    void delete (@PathVariable Integer id);
}
