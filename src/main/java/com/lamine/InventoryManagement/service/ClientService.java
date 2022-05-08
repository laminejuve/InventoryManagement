package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto getClient (Integer id);
    List<ClientDto> getAllClient ();
    ClientDto create (ClientDto clientDto);
    void delete (Integer id);
    
}
