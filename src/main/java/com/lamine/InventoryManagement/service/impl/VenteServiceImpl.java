package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ArticleDto;
import com.lamine.InventoryManagement.dto.LigneVenteDto;
import com.lamine.InventoryManagement.dto.MvmStockDto;
import com.lamine.InventoryManagement.dto.VenteDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.*;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.LigneVenteRepository;
import com.lamine.InventoryManagement.repository.VenteRepository;
import com.lamine.InventoryManagement.service.MvmStockService;
import com.lamine.InventoryManagement.service.VenteService;
import com.lamine.InventoryManagement.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    MvmStockService mvmStockService;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, LigneVenteRepository ligneVenteRepository, ArticleRepository articleRepository, MvmStockService mvmStockService) {
        this.venteRepository = venteRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
        this.mvmStockService = mvmStockService;
    }

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
            updateMvtStk(ligneVente);
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
    private void updateMvtStk (LigneVente ligneVente){
            MvmStockDto entreeStock = MvmStockDto.builder()
                    .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvmStk(SourceMvmStk.VENTE)
                    .quantity(ligneVente.getQuantity())
                    .idEntreprise(ligneVente.getIdEntreprise())
                    .build();
            mvmStockService.entreeStock(entreeStock);
    }
}
