package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.CommandeFornisseurDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeFornisseurDto;
import com.lamine.InventoryManagement.model.EtatCommande;
import com.lamine.InventoryManagement.model.LigneCommandeFornisseur;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFornisseurService {

    CommandeFornisseurDto getCmdFornisseur(Integer id);
    List<CommandeFornisseurDto> getAllCmdFornisseur();
    CommandeFornisseurDto create (CommandeFornisseurDto commandeFornisseurDto);
    void delete (Integer id);
    CommandeFornisseurDto updateEtatCommande (Integer idCommande , EtatCommande etatCommande);
    CommandeFornisseurDto updateQuantityCommande (Integer idCommande , Integer idLigneCommande , BigDecimal quantity);
    CommandeFornisseurDto updateFornisseur (Integer idCommande , Integer idFornisseur);

    CommandeFornisseurDto updateArticle (Integer idCommande , Integer idLigneCommande , Integer newIdArticle);

    // delete article ==> delete ligne commande
    CommandeFornisseurDto deleteArticle (Integer idCommande , Integer idLigneCommande);

    List<LigneCommandeFornisseurDto> findAllLigneCommandeFornisseurByCommandeFornisseurId (Integer idCommande);

}
