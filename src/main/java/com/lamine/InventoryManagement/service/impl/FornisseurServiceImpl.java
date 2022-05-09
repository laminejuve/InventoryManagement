package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.FornisseurDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Fornisseur;
import com.lamine.InventoryManagement.repository.FornisseurRepository;
import com.lamine.InventoryManagement.service.FornisseurService;
import com.lamine.InventoryManagement.validator.FornisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FornisseurServiceImpl implements FornisseurService {

    FornisseurRepository fornisseurRepository;
    @Autowired
    public FornisseurServiceImpl(FornisseurRepository fornisseurRepository) {
        this.fornisseurRepository = fornisseurRepository;
    }

    @Override
    public FornisseurDto getFornisseur(Integer id) {

        if (id == null) {
            log.error("the Fornisseur Id is null");
            return null;
        }
        Optional<Fornisseur> fornisseur = fornisseurRepository.findById(id);
        return Optional.of(FornisseurDto.fromEntity(fornisseur.get())).orElseThrow(
                ()-> new EntityNotFoundException("no Fornisseur with this id", ErrorCode.FORNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public List<FornisseurDto> getAllFornisseur() {
        return fornisseurRepository.findAll().stream().map(FornisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public FornisseurDto create(FornisseurDto fornisseurDto) {

        List<String> errors = FornisseurValidator.validate(fornisseurDto);
        if (!errors.isEmpty()){
            log.error("Fornisseur is not valid {}",fornisseurDto);
            throw  new EntityInvalidException("fornisseur not valid",ErrorCode.FORNISSEUR_NOT_VALID,errors);
        }

        return FornisseurDto.fromEntity(fornisseurRepository.save(FornisseurDto.toEntity(fornisseurDto)));
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("the Fornisseur Id is null");
            return ;
        }
        fornisseurRepository.deleteById(id);

    }
}
