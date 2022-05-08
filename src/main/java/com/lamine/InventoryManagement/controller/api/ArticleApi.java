package com.lamine.InventoryManagement.controller.api;


import com.lamine.InventoryManagement.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping (path = APP_ROOT + "/article/create" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping (value = APP_ROOT+"/article/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/article/{codeArticle}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable  String codeArticle);

    @GetMapping (value = APP_ROOT+"/article/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping (value = APP_ROOT+"/article/delete/{id}")
    void delete (@PathVariable Integer id);
}
