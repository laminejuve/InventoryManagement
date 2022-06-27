package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.model.EtatCommande;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @GetMapping (value = APP_ROOT+"/commandeClients/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto getCommandeClient(@PathVariable Integer id);

    @GetMapping (value = APP_ROOT+"/commandeClients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> getCommandeClient();

    @PostMapping(value = APP_ROOT+"/commandeClients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto create(@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(value = APP_ROOT+"/commandeClients/update/etat/{idCommande}/{etatCommande}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto updateEtatCommande(@PathVariable Integer idCommande,@PathVariable EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT+"/commandeClients/update/quantity/{idCommande}/{idLigneCommande}/{quantity}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto updateQuantity(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande , @PathVariable BigDecimal quantity);

    @PatchMapping(value = APP_ROOT+"/commandeClients/update/client/{idCommande}/{idClient}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto updateClient(@PathVariable Integer idCommande,@PathVariable Integer idClient);

    @PatchMapping(value = APP_ROOT+"/commandeClients/update/article/{idCommande}/{idLigneCommande}/{idArticle}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto updateArticle(@PathVariable Integer idCommande,@PathVariable Integer idLigneCommande ,@PathVariable Integer idArticle);

    @DeleteMapping(value = APP_ROOT+"/commandeClients/delete/{id}")
    void delete(@PathVariable Integer id);

}
