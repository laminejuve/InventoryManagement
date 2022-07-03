package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.model.Client;
import com.lamine.InventoryManagement.service.ClientService;
import com.lamine.InventoryManagement.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service ("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto>{

    private FlickrService flickrService ;
    private ClientService clientService ;
    @Autowired
    public SaveClientPhoto(FlickrService flickrService, ClientService clientService) {
        this.flickrService = flickrService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(Integer id, InputStream photo, String titre) throws FlickrException {

        ClientDto clientDto = clientService.getClient(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error while saving client's photo" , ErrorCode.UPDATE_PHOTO_EXCEPTION) ;

        }
        clientDto.setPhoto(urlPhoto);
        return clientService.create(clientDto);
    }
}
