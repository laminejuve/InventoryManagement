package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.model.*;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.ClientRepository;
import com.lamine.InventoryManagement.repository.CommandeClientRepository;
import com.lamine.InventoryManagement.repository.LigneCommandeClientRepository;
import com.lamine.InventoryManagement.service.CommandeClientService;
import com.lamine.InventoryManagement.validator.CommandeClientValidator;
import com.lamine.InventoryManagement.validator.LigneCommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    CommandeClientRepository commandeClientRepository;
    ClientRepository clientRepository;
    LigneCommandeClientRepository ligneCommandeClientRepository;
    ArticleRepository articleRepository;
    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto getCmdClient(Integer id) {

        if (id == null){
            log.error(" the commande client id is null");
            return null;
        }
        Optional<CommandeClient> commandeClient = commandeClientRepository.findById(id);
        if (commandeClient.isPresent()){
            CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient.get());
            commandeClientDto.setClient(ClientDto.fromEntity(commandeClient.get().getClient()));
            return commandeClientDto ;
        }
        throw new EntityNotFoundException("no commande client with this id",ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
    }

    @Override
    public List<CommandeClientDto> getAllCmdClients() {
        List<CommandeClient> commandeClients = commandeClientRepository.findAll();
        List<CommandeClientDto> commandeClientDTOs = new ArrayList<>();
        commandeClients.forEach(commandeClient -> {
            CommandeClientDto commandeClientDto = CommandeClientDto.fromEntity(commandeClient);
            commandeClientDto.setClient(ClientDto.fromEntity(commandeClient.getClient()));
            commandeClientDTOs.add(commandeClientDto);
        });
        return commandeClientDTOs ;
    }

    @Override
    public CommandeClientDto create(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()){
            log.error("Commande client not valid {}",commandeClientDto);
            throw new EntityInvalidException("commande client not valid",ErrorCode.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        if (commandeClientDto.getId() != null && commandeClientDto.isCommandeLiveree()){
            throw new InvalidOperationException("impossible to update a client comande when it was delivred",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (!client.isPresent()){
            throw new EntityNotFoundException("no client with this ID",ErrorCode.CLIENT_NOT_FOUND);
        }
        commandeClientDto.getLigneCommandeClients().forEach(
                ligneCommandeClient -> {
                    List<String> listErrors = LigneCommandeClientValidator.validate(ligneCommandeClient);
                    if (!listErrors.isEmpty()){
                        log.error("ligne de commande not valid {}",ligneCommandeClient);
                        throw  new EntityInvalidException("ligne de commande not valid",ErrorCode.LIGNE_COMMANDE_CLIENT_NOT_VALID,listErrors);
                    }
                    Optional<Article> article = articleRepository.findById(ligneCommandeClient.getArticle().getId());
                    if (!article.isPresent()){
                        log.error("no article with this id");
                        throw new EntityNotFoundException("no article woth this id",ErrorCode.ARTICLE_NOT_FOUND);
                    }
                }
        );
        CommandeClient commandeClient = CommandeClientDto.toEntity(commandeClientDto);
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
        CommandeClient savedCommandeClient = commandeClientRepository.save(commandeClient);
        commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt -> {
            LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCmdClt);
            ligneCommandeClient.setCommandeClient(savedCommandeClient);
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });
        return CommandeClientDto.fromEntity(savedCommandeClient) ;

        /*CommandeClient commandeClient = CommandeClientDto.toEntity(commandeClientDto);
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
        commandeClient.setLigneCommandeClients(commandeClientDto.getLigneCommandeClients().stream().map(
                LigneCommandeClientDto::toEntity).collect(Collectors.toList()));
        commandeClient.getLigneCommandeClients().forEach(ligneCommandeClient -> ligneCommandeClientRepository.save(ligneCommandeClient));
        return CommandeClientDto.fromEntity(commandeClientRepository.save(commandeClient));*/

    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("the Commande client id is null");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }


    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {

        checkIdCommande(idCommande);
        if (etatCommande == null){
            log.error(" etat of Commande client  is null ");
            throw new InvalidOperationException("impossible to update a client commande with an etat null ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto cmdClient = checkEtatCommande(idCommande);

        cmdClient.setEtatCommande(etatCommande);
        CommandeClient updatedCmd = commandeClientRepository.save(CommandeClientDto.toEntity(cmdClient));

        return CommandeClientDto.fromEntity(updatedCmd);
    }

    @Override
    public CommandeClientDto updateQuantityCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {

       checkIdCommande(idCommande);
       checkIdLigneCommande(idLigneCommande);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0){
            log.error(" quantity is null or zero ");
            throw new InvalidOperationException("impossible to update a client commande quantity with a null or zero ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto cmdClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantity(quantity);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return cmdClient;
    }



    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

        checkIdCommande(idCommande);

        if (idClient == null){
            log.error("the client id is null ");
            throw new InvalidOperationException("impossible to update a client  with a null id client ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }

        CommandeClientDto cmdClient = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if (clientOptional.isEmpty()){
            throw new EntityNotFoundException("no Client found with this id" ,ErrorCode.CLIENT_NOT_FOUND);
        }
        cmdClient.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(cmdClient)));
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer newIdArticle) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(newIdArticle,"new");
        CommandeClientDto cmdClient = checkEtatCommande(idCommande);
        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);
        Optional<Article> articleOptional = articleRepository.findById(newIdArticle);
        if (articleOptional.isEmpty()){
            log.error("no article found with this id");
            throw  new EntityNotFoundException("no article found with this id",ErrorCode.ARTICLE_NOT_FOUND);
        }
        ligneCommandeClientOptional.get().setArticle(articleOptional.get());
        ligneCommandeClientRepository.save(ligneCommandeClientOptional.get());
        return cmdClient;
    }

    private void checkIdCommande (Integer idCommande) {
        if (idCommande == null){
            log.error(" Commande client id is null ");
            throw new InvalidOperationException("impossible to update a client commande with an ID null ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
    }
    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null){
            log.error(" Ligne Commande client id is null ");
            throw new InvalidOperationException("impossible to update a client commande with a null id ligne commande ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
    }
    private void checkIdArticle(Integer idArticle , String msg) {
        if (idArticle == null){
            log.error(" ID "+msg+" article is null ");
            throw new InvalidOperationException("impossible to update a client commande with a null id "+msg+" article ",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
    }
    private CommandeClientDto checkEtatCommande (Integer idCommande){
        CommandeClientDto cmdClient = getCmdClient(idCommande);

        if (cmdClient.isCommandeLiveree()){
            throw new InvalidOperationException("impossible to update a client comande when it was delivred",ErrorCode.COMMANDE_CLIENT_NOT_MODIFIABLE);
        }
        return cmdClient ;
    }
    private Optional<LigneCommandeClient> findLigneCommandeClient( Integer idLigneCommande){
        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);

        if (ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException("no ligne commande found with this id" +
                    "ligneCommande",ErrorCode.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        return ligneCommandeClientOptional;
    }
}
