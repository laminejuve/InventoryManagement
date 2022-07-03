package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.dto.UtilisateurDto;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.service.FlickrService;
import com.lamine.InventoryManagement.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service ("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private UtilisateurService utilisateurService;
    private FlickrService flickrService;
    @Autowired
    public SaveUtilisateurPhoto(UtilisateurService utilisateurService, FlickrService flickrService) {
        this.utilisateurService = utilisateurService;
        this.flickrService = flickrService;
    }

    @Override
    public UtilisateurDto save(Integer id , InputStream photo, String titre) throws FlickrException {
        UtilisateurDto utilisateurDto = utilisateurService.getUtilisateur(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error while saving utilisateur's photo" , ErrorCode.UPDATE_PHOTO_EXCEPTION) ;

        }
        utilisateurDto.setPhoto(urlPhoto);
        return utilisateurService.create(utilisateurDto);
    }
}
