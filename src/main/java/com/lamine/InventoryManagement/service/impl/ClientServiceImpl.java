package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ClientDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Client;
import com.lamine.InventoryManagement.repository.ClientRepository;
import com.lamine.InventoryManagement.service.ClientService;
import com.lamine.InventoryManagement.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto getClient(Integer id) {

        if (id == null){
            log.error("the Client Id is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(
                ()->  new EntityNotFoundException("there is no Client with this ID", ErrorCode.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> getAllClient() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ClientDto create(ClientDto clientDto) {

        List<String> errors = ClientValidator.validate((clientDto));
        if (!errors.isEmpty()){
            log.error(" the Client is not valid {}",clientDto);
            throw new EntityInvalidException("Client not valid",ErrorCode.CLIENT_NOT_VALID,errors);
        }

        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("The client ID is null");
            return ;
        }
        clientRepository.deleteById(id);

    }
}
