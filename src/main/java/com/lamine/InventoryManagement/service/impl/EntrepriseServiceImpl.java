package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.EntrepriseDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Entreprise;
import com.lamine.InventoryManagement.repository.EntrepriseRepository;
import com.lamine.InventoryManagement.service.EntrepriseService;
import com.lamine.InventoryManagement.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto getEntreprise(Integer id) {
        if (id == null){
            log.error("the Entreprise Id is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(
                ()-> new EntityNotFoundException("there is no Entreprise with this id", ErrorCode.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public List<EntrepriseDto> getAllEntreprise() {
        return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(
                                                                      Collectors.toList());
    }

    @Override
    public EntrepriseDto create(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("the entreprise is not valid {}",entrepriseDto);
            throw  new EntityInvalidException("the entreprise is not valid",ErrorCode.ENTREPRISE_NOT_VALID,errors);
        }

        return  EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));

    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("the entreprise id is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
