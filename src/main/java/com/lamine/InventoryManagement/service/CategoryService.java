package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);
    CategoryDto  findById(Integer id);
  //  CategoryDto findByCode(String code);
    List<CategoryDto> findAll();
    void delete (Integer id);


}
