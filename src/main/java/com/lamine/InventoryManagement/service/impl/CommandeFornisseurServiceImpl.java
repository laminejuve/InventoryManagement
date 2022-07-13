package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.*;
import com.lamine.InventoryManagement.dto.CommandeFornisseurDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.model.*;
import com.lamine.InventoryManagement.model.CommandeFornisseur;
import com.lamine.InventoryManagement.repository.ArticleRepository;
import com.lamine.InventoryManagement.repository.CommandeFornisseurRepository;
import com.lamine.InventoryManagement.repository.FornisseurRepository;
import com.lamine.InventoryManagement.repository.LigneCommandeFornisseurRepository;
import com.lamine.InventoryManagement.service.CommandeFornisseurService;
import com.lamine.InventoryManagement.service.MvmStockService;
import com.lamine.InventoryManagement.validator.CommandeFornisseurValidator;
import com.lamine.InventoryManagement.validator.LigneCommandeFornisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFornisseurServiceImpl implements CommandeFornisseurService {

    CommandeFornisseurRepository commandeFornisseurRepository;
    FornisseurRepository fornisseurRepository;
    ArticleRepository articleRepository;
    LigneCommandeFornisseurRepository ligneCommandeFornisseurRepository;
    MvmStockService mvmStockService;
    @Autowired
    public CommandeFornisseurServiceImpl(CommandeFornisseurRepository commandeFornisseurRepository, FornisseurRepository fornisseurRepository, ArticleRepository articleRepository, LigneCommandeFornisseurRepository ligneCommandeFornisseurRepository ,MvmStockService mvmStockService) {
        this.commandeFornisseurRepository = commandeFornisseurRepository;
        this.fornisseurRepository = fornisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFornisseurRepository = ligneCommandeFornisseurRepository;
        this.mvmStockService = mvmStockService;
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

    @Override
    public CommandeFornisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {

        checkIdCommande(idCommande);
        if (etatCommande == null){
            log.error(" etat of Commande Fornisseur  is null ");
            throw new InvalidOperationException("impossible to update a Fornisseur commande with an etat null ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }

        CommandeFornisseurDto cmdFornisseur = checkEtatCommande(idCommande);

        cmdFornisseur.setEtatCommande(etatCommande);
        CommandeFornisseur updatedCmd = commandeFornisseurRepository.save(CommandeFornisseurDto.toEntity(cmdFornisseur));
        if (cmdFornisseur.isCommandeLiveree()){
            updateMvtStk(idCommande);
        }
        return CommandeFornisseurDto.fromEntity(updatedCmd);
    }

    @Override
    public CommandeFornisseurDto updateQuantityCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0){
            log.error(" quantity is null or zero ");
            throw new InvalidOperationException("impossible to update a Fornisseur commande quantity with a null or zero ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }

        CommandeFornisseurDto cmdFornisseur = checkEtatCommande(idCommande);

        Optional<LigneCommandeFornisseur> ligneCommandeFornisseurOptional = findLigneCommandeFornisseur(idLigneCommande);
        LigneCommandeFornisseur ligneCommandeFornisseur = ligneCommandeFornisseurOptional.get();
        ligneCommandeFornisseur.setQuantity(quantity);
        ligneCommandeFornisseurRepository.save(ligneCommandeFornisseur);
        return cmdFornisseur;
    }



    @Override
    public CommandeFornisseurDto updateFornisseur(Integer idCommande, Integer idFornisseur) {

        checkIdCommande(idCommande);

        if (idFornisseur == null){
            log.error("the Fornisseur id is null ");
            throw new InvalidOperationException("impossible to update a Fornisseur  with a null id Fornisseur ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }

        CommandeFornisseurDto cmdFornisseur = checkEtatCommande(idCommande);

        Optional<Fornisseur> FornisseurOptional = fornisseurRepository.findById(idFornisseur);
        if (FornisseurOptional.isEmpty()){
            throw new EntityNotFoundException("no Fornisseur found with this id" ,ErrorCode.FORNISSEUR_NOT_FOUND);
        }
        cmdFornisseur.setFornisseur(FornisseurDto.fromEntity(FornisseurOptional.get()));

        return CommandeFornisseurDto.fromEntity(commandeFornisseurRepository.save(CommandeFornisseurDto.toEntity(cmdFornisseur)));
    }

    @Override
    public CommandeFornisseurDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer newIdArticle) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(newIdArticle,"new");
        CommandeFornisseurDto cmdFornisseur = checkEtatCommande(idCommande);
        Optional<LigneCommandeFornisseur> ligneCommandeFornisseurOptional = findLigneCommandeFornisseur(idLigneCommande);
        Optional<Article> articleOptional = articleRepository.findById(newIdArticle);
        if (articleOptional.isEmpty()){
            log.error("no article found with this id");
            throw  new EntityNotFoundException("no article found with this id",ErrorCode.ARTICLE_NOT_FOUND);
        }
        ligneCommandeFornisseurOptional.get().setArticle(articleOptional.get());
        ligneCommandeFornisseurRepository.save(ligneCommandeFornisseurOptional.get());
        return cmdFornisseur;
    }

    @Override
    public CommandeFornisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFornisseurDto cmdFornisseur = checkEtatCommande(idCommande);
        //just to check the ligne commande and inform the Fornisseur in case it doesnt exist
        findLigneCommandeFornisseur(idLigneCommande);
        ligneCommandeFornisseurRepository.deleteById(idLigneCommande);

        return cmdFornisseur;
    }

    @Override
    public List<LigneCommandeFornisseurDto> findAllLigneCommandeFornisseurByCommandeFornisseurId(Integer idCommande) {
        return ligneCommandeFornisseurRepository.findAllByCommandeFornisseurId(idCommande).stream()
                .map(LigneCommandeFornisseurDto::fromEntity).collect(Collectors.toList());
    }

    private void checkIdCommande (Integer idCommande) {
        if (idCommande == null){
            log.error(" Commande Fornisseur id is null ");
            throw new InvalidOperationException("impossible to update a Fornisseur commande with an ID null ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }
    }
    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null){
            log.error(" Ligne Commande Fornisseur id is null ");
            throw new InvalidOperationException("impossible to update a Fornisseur commande with a null id ligne commande ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }
    }
    private void checkIdArticle(Integer idArticle , String msg) {
        if (idArticle == null){
            log.error(" ID "+msg+" article is null ");
            throw new InvalidOperationException("impossible to update a Fornisseur commande with a null id "+msg+" article ",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }
    }
    private CommandeFornisseurDto checkEtatCommande (Integer idCommande){
        CommandeFornisseurDto cmdFornisseur = getCmdFornisseur(idCommande);

        if (cmdFornisseur.isCommandeLiveree()){
            throw new InvalidOperationException("impossible to update a Fornisseur comande when it was delivred",ErrorCode.COMMANDE_FORNISSEUR_NOT_MODIFIABLE);
        }
        return cmdFornisseur ;
    }
    private Optional<LigneCommandeFornisseur> findLigneCommandeFornisseur( Integer idLigneCommande){
        Optional<LigneCommandeFornisseur> ligneCommandeFornisseurOptional = ligneCommandeFornisseurRepository.findById(idLigneCommande);

        if (ligneCommandeFornisseurOptional.isEmpty()){
            throw new EntityNotFoundException("no ligne commande found with this id" +
                    "ligneCommande",ErrorCode.LIGNE_COMMANDE_FORNISSEUR_NOT_FOUND);
        }
        return ligneCommandeFornisseurOptional;
    }

    private void updateMvtStk (Integer idCommande){
        List<LigneCommandeFornisseur> ligneCommandeFornisseurs = ligneCommandeFornisseurRepository.findAllByCommandeFornisseurId(idCommande);
        ligneCommandeFornisseurs.forEach(ligne -> {
            MvmStockDto entreeStock = MvmStockDto.builder()
                    .article(ArticleDto.fromEntity(ligne.getArticle()))
                    .dateMvt(Instant.now())
                    .typeMvt(TypeMvtStk.ENTREE)
                    .sourceMvmStk(SourceMvmStk.COMMANDE_FORNISSEUR)
                    .quantity(ligne.getQuantity())
                    .idEntreprise(ligne.getIdEntreprise())
                    .build();
            mvmStockService.entreeStock(entreeStock);
        });
    }
}
