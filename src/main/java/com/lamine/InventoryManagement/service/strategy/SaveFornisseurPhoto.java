package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.dto.FornisseurDto;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.service.FlickrService;
import com.lamine.InventoryManagement.service.FornisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service ("fornisseurStrategy")
@Slf4j
public class SaveFornisseurPhoto implements Strategy<FornisseurDto> {

    private FornisseurService fornisseurService;
    private FlickrService flickrService;
    @Autowired
    public SaveFornisseurPhoto(FornisseurService fornisseurService, FlickrService flickrService) {
        this.fornisseurService = fornisseurService;
        this.flickrService = flickrService;
    }

    @Override
    public FornisseurDto save(Integer id , InputStream photo, String titre) throws FlickrException {
        FornisseurDto fornisseurDto = fornisseurService.getFornisseur(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error while saving fornisseur's photo" , ErrorCode.UPDATE_PHOTO_EXCEPTION) ;

        }
        fornisseurDto.setPhoto(urlPhoto);
        return fornisseurService.create(fornisseurDto);
    }
}
