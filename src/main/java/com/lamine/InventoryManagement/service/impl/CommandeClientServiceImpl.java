package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.model.Client;
import com.lamine.InventoryManagement.model.CommandeClient;
import com.lamine.InventoryManagement.model.LigneCommandeClient;
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
}