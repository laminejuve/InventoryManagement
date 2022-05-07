package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.CategoryApi;
import com.lamine.InventoryManagement.dto.CategoryDto;
import com.lamine.InventoryManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoyController implements CategoryApi {

    CategoryService categoryService;
    @Autowired
    public CategoyController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
      categoryService.delete(id);
    }
}
