package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface UtilisateurApi {


    @GetMapping (value = APP_ROOT+"/utilisateurs/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto getUtilisateur (@PathVariable  Integer id);

    @GetMapping (value = APP_ROOT+"/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> getAllUtilisateurs ();

    @PostMapping  (value = APP_ROOT+"/utilisateurs/create",consumes =MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto create (@RequestBody UtilisateurDto utilisateurDto);

    @DeleteMapping (value = APP_ROOT+"/utilisateurs/delete/{id}")
    void delete (@PathVariable Integer id);


}
