package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.ClientApi;
import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto getClient(Integer id) {
        return clientService.getClient(id);
    }

    @Override
    public List<ClientDto> getAllClient() {
        return clientService.getAllClient();
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        return clientService.create(clientDto);
    }

    @Override
    public void delete(Integer id) {
       clientService.delete(id);
    }
}
