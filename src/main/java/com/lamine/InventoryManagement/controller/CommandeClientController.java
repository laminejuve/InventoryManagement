package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.CommandeClientApi;
import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
import com.lamine.InventoryManagement.model.EtatCommande;
import com.lamine.InventoryManagement.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

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
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return commandeClientService.updateEtatCommande(idCommande,etatCommande);
    }


    @Override
    public CommandeClientDto updateQuantity(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {
        return commandeClientService.updateQuantityCommande(idCommande,idLigneCommande,quantity);
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        return commandeClientService.updateClient(idCommande,idClient);
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return commandeClientService.updateArticle(idCommande,idLigneCommande,idArticle);
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return commandeClientService.deleteArticle(idCommande,idLigneCommande);
    }

    @Override
    public void delete(Integer id) { commandeClientService.delete(id); }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idCommande) {
        return commandeClientService.findAllLigneCommandeClientByCommandeClientId(idCommande);
    }
}
