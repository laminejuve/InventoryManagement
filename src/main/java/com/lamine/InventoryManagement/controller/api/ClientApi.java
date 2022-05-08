package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface ClientApi {

    @GetMapping (value = APP_ROOT+"/client/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto getClient(@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/client/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> getAllClient();

    @PostMapping (value = APP_ROOT+"/client/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto create (@RequestBody ClientDto clientDto);

    @DeleteMapping (value = APP_ROOT+"/client/delete/{id}")
    void delete (@PathVariable Integer id);


}
