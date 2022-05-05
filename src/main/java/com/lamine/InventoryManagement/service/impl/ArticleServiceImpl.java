package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ArticleDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.service.ArticleService;
import com.lamine.InventoryManagement.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()){
            log.error("Article is not Valid {}",articleDto);
            throw  new EntityInvalidException("L'article n'est pas valide" , ErrorCode.ARTICLE_NOT_VALID,errors);
        }
        return null;
    }

    @Override
    public ArticleDto findById(Integer id) {
        return null;
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return null;
    }

    @Override
    public List<ArticleDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
