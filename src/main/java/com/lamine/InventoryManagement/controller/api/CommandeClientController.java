package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements  CommandeClientApi{

    CommandeClientService commandeClientService;
    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto getCommandeClient(Integer id) {
        return commandeClientService.getCmdClient(id);
    }

    @Override
    public List<CommandeClientDto> getCommandeClient() {
        return commandeClientService.getAllCmdClients();
    }

    @Override
    public CommandeClientDto create(CommandeClientDto commandeClientDto) {
        return commandeClientService.create(commandeClientDto);
    }

    @Override
    public void delete(Integer id) {
      commandeClientService.delete(id);
    }
}
