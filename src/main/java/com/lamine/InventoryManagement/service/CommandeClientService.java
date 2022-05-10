package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto getCmdClient(Integer id);
    List<CommandeClientDto> getAllCmdClients ();
    CommandeClientDto create (CommandeClientDto commandeClientDto);
    void delete (Integer id);

}
