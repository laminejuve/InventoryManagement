package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.CategoryDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Category;
import com.lamine.InventoryManagement.repository.CategoryRepository;
import com.lamine.InventoryManagement.service.CategoryService;
import com.lamine.InventoryManagement.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Category not valid {}",categoryDto);
            throw new EntityInvalidException("category not valid", ErrorCode.CATEGORY_NOT_VALID,errors);
        }
        Category category = categoryRepository.save(CategoryDto.toEntity(categoryDto));
        return CategoryDto.fromEntity(category);
    }

    @Override
    public CategoryDto findById(Integer id) {

        if( id == null){
            log.error("Category id is null");
            return null ;
        }
        Optional<Category> category = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(
                () -> new EntityNotFoundException("category not exist with this id", ErrorCode.CATEGORY_NOT_FOUND));

    }


   /* public CategoryDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Category code is null");
            return null ;
        }
        Optional<Category> category = categoryRepository.findByCode(code);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(
                () -> new EntityNotFoundException("category not exist with this code", ErrorCode.CATEGORY_NOT_FOUND));

    }*/

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(
                        CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Category id is null");
            return ;
        }
       categoryRepository.deleteById(id);
    }
}
