package com.lamine.InventoryManagement.controller.api;

import com.flickr4java.flickr.FlickrException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;
@RestController
public interface PhotoApi {

    @PostMapping (path = APP_ROOT + "/photos/{id}/{title}/{context}/" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    Object save (@PathVariable String context , @PathVariable Integer id , @RequestParam("file") MultipartFile photo , @PathVariable String title) throws IOException, FlickrException;
}
