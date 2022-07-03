package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.dto.EntrepriseDto;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.service.EntrepriseService;
import com.lamine.InventoryManagement.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service ("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

    private EntrepriseService entrepriseService;
    private FlickrService flickrService;
    @Autowired
    public SaveEntreprisePhoto(EntrepriseService entrepriseService, FlickrService flickrService) {
        this.entrepriseService = entrepriseService;
        this.flickrService = flickrService;
    }

    @Override
    public EntrepriseDto save(Integer id ,InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.getEntreprise(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error while saving entreprise's photo" , ErrorCode.UPDATE_PHOTO_EXCEPTION) ;
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.create(entrepriseDto);
    }
}
