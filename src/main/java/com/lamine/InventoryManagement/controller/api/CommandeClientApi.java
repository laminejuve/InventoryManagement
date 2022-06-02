package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @GetMapping (value = APP_ROOT+"/commandeClients/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto getCommandeClient(@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/commandeClients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> getCommandeClient();

    @PostMapping(value = APP_ROOT+"/commandeClients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto create(@RequestBody CommandeClientDto commandeClientDto);

    @DeleteMapping(value = APP_ROOT+"/commandeClients/delete/{id}")
    void delete(@PathVariable Integer id);
}