package com.lamine.InventoryManagement.controller;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.controller.api.PhotoApi;
import com.lamine.InventoryManagement.service.strategy.StrategyPhotoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class PhotoController implements PhotoApi {
    @Autowired
    private StrategyPhotoContext strategyPhotoContext;
    @Override
    public Object save(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.save(context,id,photo.getInputStream(),title);
    }
}
