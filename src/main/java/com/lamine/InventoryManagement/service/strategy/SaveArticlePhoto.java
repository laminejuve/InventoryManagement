package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.dto.ArticleDto;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.service.ArticleService;
import com.lamine.InventoryManagement.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service ("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto>{

    private FlickrService flickrService;
    private ArticleService articleService;
    
    @Autowired
    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(Integer id, InputStream photo, String titre) throws FlickrException {

        ArticleDto articleDto = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error while saving article's photo" , ErrorCode.UPDATE_PHOTO_EXCEPTION) ;
        }
        articleDto.setPhoto(urlPhoto);
        return articleService.save(articleDto);
    }
}
