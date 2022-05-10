package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.LigneCommandeClientDto;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Client;
import com.lamine.InventoryManagement.model.CommandeClient;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.ClientRepository;
import com.lamine.InventoryManagement.repository.CommandeClientRepository;
import com.lamine.InventoryManagement.repository.LigneCommandeClientRepository;
import com.lamine.InventoryManagement.service.CommandeClientService;
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
            commandeClientDto.setLigneCommandeClients(commandeClient.get().getLigneCommandeClients().stream().map(
                    LigneCommandeClientDto::fromEntity).collect(Collectors.toList()));
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
            commandeClientDto.setLigneCommandeClients(commandeClient.getLigneCommandeClients().stream().map(
                    LigneCommandeClientDto::fromEntity).collect(Collectors.toList()));
            commandeClientDTOs.add(commandeClientDto);
        });
        return commandeClientDTOs ;
    }

    @Override
    public CommandeClientDto create(CommandeClientDto commandeClientDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
