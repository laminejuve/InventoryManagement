package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.EntrepriseDto;
import com.lamine.InventoryManagement.service.EntrepriseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface EntrepriseApi {

    @GetMapping (value = APP_ROOT+"/entreprise{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
   EntrepriseDto getEntreprise (@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/entreprise/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> getAllEntreprise ();

    @PostMapping (value = APP_ROOT+"/entreprise/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto create (@RequestBody EntrepriseDto entrepriseDto);

    @DeleteMapping (value = APP_ROOT+"/entreprise/delete/{id}")
    void delete (@PathVariable  Integer id);

}
