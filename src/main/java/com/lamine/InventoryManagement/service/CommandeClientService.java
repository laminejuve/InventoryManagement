package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto getCmdClient(Integer id);
    List<CommandeClientDto> getAllCmdClients ();
    CommandeClientDto create (CommandeClientDto commandeClientDto);
    void delete (Integer id);
    CommandeClientDto updateEtatCommande (Integer idCommande , EtatCommande etatCommande);
    CommandeClientDto updateQuantityCommande (Integer idCommande , Integer idLigneCommande , BigDecimal quantity);

}
