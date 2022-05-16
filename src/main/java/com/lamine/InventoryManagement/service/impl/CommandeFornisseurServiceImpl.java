package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.*;
import com.lamine.InventoryManagement.dto.CommandeFornisseurDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.*;
import com.lamine.InventoryManagement.model.CommandeFornisseur;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.CommandeFornisseurRepository;
import com.lamine.InventoryManagement.repository.FornisseurRepository;
import com.lamine.InventoryManagement.repository.LigneCommandeFornisseurRepository;
import com.lamine.InventoryManagement.service.CommandeFornisseurService;
import com.lamine.InventoryManagement.validator.CommandeFornisseurValidator;
import com.lamine.InventoryManagement.validator.CommandeFornisseurValidator;
import com.lamine.InventoryManagement.validator.LigneCommandeFornisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommandeFornisseurServiceImpl implements CommandeFornisseurService {

    CommandeFornisseurRepository commandeFornisseurRepository;
    FornisseurRepository fornisseurRepository;
    ArticleRepository articleRepository;
    LigneCommandeFornisseurRepository ligneCommandeFornisseurRepository;
    @Autowired
    public CommandeFornisseurServiceImpl(CommandeFornisseurRepository commandeFornisseurRepository, FornisseurRepository fornisseurRepository, ArticleRepository articleRepository, LigneCommandeFornisseurRepository ligneCommandeFornisseurRepository) {
        this.commandeFornisseurRepository = commandeFornisseurRepository;
        this.fornisseurRepository = fornisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFornisseurRepository = ligneCommandeFornisseurRepository;
    }




    @Override
    public CommandeFornisseurDto getCmdFornisseur(Integer id) {

        if (id == null){
            log.error(" the commande fornisseur id is null");
            return null;
        }
        Optional<CommandeFornisseur> commandeFornisseur = commandeFornisseurRepository.findById(id);
        if (commandeFornisseur.isPresent()){
            CommandeFornisseurDto commandeFornisseurDto = CommandeFornisseurDto.fromEntity(commandeFornisseur.get());
            commandeFornisseurDto.setFornisseur(FornisseurDto.fromEntity(commandeFornisseur.get().getFornisseur()));
            return commandeFornisseurDto ;
        }
        throw new EntityNotFoundException("no commande Fornisseur with this id", ErrorCode.COMMANDE_FORNISSEUR_NOT_FOUND);
    }

    @Override
    public List<CommandeFornisseurDto> getAllCmdFornisseur() {
        List<CommandeFornisseur> commandeFornisseurs = commandeFornisseurRepository.findAll();
        List<CommandeFornisseurDto> commandeFornisseurDTOs = new ArrayList<>();
        commandeFornisseurs.forEach(commandeFornisseur -> {
            CommandeFornisseurDto commandeFornisseurDto = CommandeFornisseurDto.fromEntity(commandeFornisseur);
            commandeFornisseurDto.setFornisseur(FornisseurDto.fromEntity(commandeFornisseur.getFornisseur()));
            commandeFornisseurDTOs.add(commandeFornisseurDto);
        });
        return commandeFornisseurDTOs ;
    }

    @Override
    public CommandeFornisseurDto create(CommandeFornisseurDto commandeFornisseurDto) {

        List<String> errors = CommandeFornisseurValidator.validate(commandeFornisseurDto);
        if (!errors.isEmpty()){
            log.error("Commande Fornisseur not valid {}",commandeFornisseurDto);
            throw new EntityInvalidException("commande Fornisseur not valid",ErrorCode.COMMANDE_FORNISSEUR_NOT_VALID,errors);
        }
        Optional<Fornisseur> fornisseur = fornisseurRepository.findById(commandeFornisseurDto.getFornisseur().getId());
        if (!fornisseur.isPresent()){
            throw new EntityNotFoundException("no Fornisseur with this ID",ErrorCode.FORNISSEUR_NOT_FOUND);
        }
        commandeFornisseurDto.getLigneCommandeFornisseurs().forEach(
                ligneCommandeFornisseur -> {
                    List<String> listErrors = LigneCommandeFornisseurValidator.validate(ligneCommandeFornisseur);
                    if (!listErrors.isEmpty()){
                        log.error("ligne de commande not valid {}",ligneCommandeFornisseur);
                        throw  new EntityInvalidException("ligne de commande not valid",ErrorCode.LIGNE_COMMANDE_FORNISSEUR_NOT_VALID,listErrors);
                    }
                    Optional<Article> article = articleRepository.findById(ligneCommandeFornisseur.getArticle().getId());
                    if (!article.isPresent()){
                        log.error("no article with this id");
                        throw new EntityNotFoundException("no article woth this id",ErrorCode.ARTICLE_NOT_FOUND);
                    }
                }
        );
        CommandeFornisseur commandeFornisseur = CommandeFornisseurDto.toEntity(commandeFornisseurDto);
        commandeFornisseur.setFornisseur(FornisseurDto.toEntity(commandeFornisseurDto.getFornisseur()));
        CommandeFornisseur savedCommandeFornisseur = commandeFornisseurRepository.save(commandeFornisseur);
        commandeFornisseurDto.getLigneCommandeFornisseurs().forEach(ligneCmdClt -> {
            LigneCommandeFornisseur ligneCommandeFornisseur = LigneCommandeFornisseurDto.toEntity(ligneCmdClt);
            ligneCommandeFornisseur.setCommandeFornisseur(savedCommandeFornisseur);
            ligneCommandeFornisseurRepository.save(ligneCommandeFornisseur);
        });
        return CommandeFornisseurDto.fromEntity(savedCommandeFornisseur) ;

        /*CommandeFornisseur commandeFornisseur = CommandeFornisseurDto.toEntity(commandeFornisseurDto);
        commandeFornisseur.setFornisseur(FornisseurDto.toEntity(commandeFornisseurDto.getFornisseur()));
        commandeFornisseur.setLigneCommandeFornisseurs(commandeFornisseurDto.getLigneCommandeFornisseurs().stream().map(
                LigneCommandeFornisseurDto::toEntity).collect(Collectors.toList()));
        commandeFornisseur.getLigneCommandeFornisseurs().forEach(ligneCommandeFornisseur -> ligneCommandeFornisseurRepository.save(ligneCommandeFornisseur));
        return CommandeFornisseurDto.fromEntity(commandeFornisseurRepository.save(commandeFornisseur));*/

    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("the Commande Fornisseur id is null");
            return ;
        }
        commandeFornisseurRepository.deleteById(id);
    }
}
