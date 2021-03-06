package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
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
    CommandeClientDto updateClient (Integer idCommande , Integer idClient);

    CommandeClientDto updateArticle (Integer idCommande , Integer idLigneCommande , Integer newIdArticle);

    // delete article ==> delete ligne commande
    CommandeClientDto deleteArticle (Integer idCommande , Integer idLigneCommande);

     List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId (Integer idCommande);



}
