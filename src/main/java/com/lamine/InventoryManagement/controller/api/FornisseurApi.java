package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.FornisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface FornisseurApi {

    @GetMapping (value = APP_ROOT+"/fornisseur/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    FornisseurDto getFronisseur (@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/fornisseur/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<FornisseurDto> getAllFronisseur ();

    @PostMapping (value = APP_ROOT+"/fornisseur/create" , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    FornisseurDto create (@RequestBody FornisseurDto fornisseurDto);

    @DeleteMapping (value = APP_ROOT+"/fornisseur/delete/{id}")
    void delete (@PathVariable Integer id);
}
