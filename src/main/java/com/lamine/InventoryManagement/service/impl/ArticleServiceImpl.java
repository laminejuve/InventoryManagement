package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ArticleDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.service.ArticleService;
import com.lamine.InventoryManagement.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("Article id is null");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(
                () -> new EntityNotFoundException(
                        " no article with this id found in the daata base",ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if (!StringUtils.hasLength(codeArticle)){
            log.error("Article code is null");
            return null;
        }

        Optional<Article> article = articleRepository.findByCodeArticle(codeArticle);
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(
                () -> new EntityNotFoundException(
                        " no article with this code found in the data base",ErrorCode.ARTICLE_NOT_FOUND));
    }


    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream().map(
                ArticleDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("Article id is null");
            return ;
        }
        articleRepository.deleteById(id);
    }
}
