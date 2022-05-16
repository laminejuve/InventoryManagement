package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.LigneVenteDto;
import com.lamine.InventoryManagement.dto.VenteDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.Article;
import com.lamine.InventoryManagement.model.LigneVente;
import com.lamine.InventoryManagement.model.Vente;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.LigneVenteRepository;
import com.lamine.InventoryManagement.repository.VenteRepository;
import com.lamine.InventoryManagement.service.VenteService;
import com.lamine.InventoryManagement.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    VenteRepository venteRepository;
    LigneVenteRepository ligneVenteRepository;
    ArticleRepository articleRepository;

    @Override
    public VenteDto getVente(Integer id) {

        if ( id == null){
            log.error("the id is null");
            return null;
        }
        return venteRepository.findById(id).map(VenteDto::fromEntity).orElseThrow(
                ()-> new EntityNotFoundException("no vente with this id ",ErrorCode.VENTE_NOT_FOUND)
        );
    }

    @Override
    public List<VenteDto> getAllVente() {

        return venteRepository.findAll().stream().map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public VenteDto create(VenteDto venteDto) {

        List<String> errors = VenteValidator.validate(venteDto);
        if (!errors.isEmpty()){
            log.error("Vente is not valid {}",venteDto);
            throw new EntityInvalidException("vente is not valid", ErrorCode.VENTE_NOT_VALID,errors);
        }
        List<String> articleErrors = new ArrayList<>();
        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()){
                articleErrors.add("no article with this id found in the DB");
            }
        });
        if (!articleErrors.isEmpty()){
            log.error("one or more article were not found in the DB {}",articleErrors);
            throw new EntityInvalidException("one or more article were not found in the DB", ErrorCode.VENTE_NOT_VALID,errors);
        }
        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));
        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            LigneVente ligneVente= LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
        });
        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public void delete(Integer id) {
        if ( id == null){
            log.error("the id is null");
            return;
        }
      venteRepository.deleteById(id);
    }
}
