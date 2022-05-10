package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
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

import java.util.List;
import java.util.Optional;

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
        if (!commandeClient.isPresent()){
            log.error("no commande client with this id");
            throw new EntityNotFoundException("no commande client with this id ", ErrorCode.COMMANDE_CLIENT_NOT_FOUND);
        }
        Integer idClient = commandeClient.get().getClient().getId();
        if (idClient == null ){

        }
        Optional<Client> client = clientRepository.findById(idClient);

        return null;
    }

    @Override
    public List<CommandeClientDto> getAllCmdClients() {
        return null;
    }

    @Override
    public CommandeClientDto create(CommandeClientDto commandeClientDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
