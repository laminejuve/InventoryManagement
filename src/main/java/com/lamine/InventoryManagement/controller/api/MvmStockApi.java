package com.lamine.InventoryManagement.controller.api;

import com.lamine.InventoryManagement.dto.CommandeClientDto;
import com.lamine.InventoryManagement.dto.MvmStockDto;
import com.lamine.InventoryManagement.model.EtatCommande;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.lamine.InventoryManagement.utils.Constants.APP_ROOT;

public interface MvmStockApi {

    @GetMapping(value = APP_ROOT+"/mvmStock/stockreel/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal stockReelArticle (@PathVariable  Integer idArticle);

    @GetMapping (value = APP_ROOT+"/mvmStock/filtre/article/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvmStockDto> mvmStockArticle (@PathVariable  Integer idArticle);

    @PostMapping(value = APP_ROOT+"/mvmStock/entree")
    MvmStockDto entreeStock (@RequestBody MvmStockDto mvmStockDto);

    @PostMapping(value = APP_ROOT+"/mvmStock/sortie")
    MvmStockDto sortieStock (@RequestBody MvmStockDto mvmStockDto);

    @PostMapping(value = APP_ROOT+"/mvmStock/correctionpos")
    MvmStockDto correctionStockPos (@RequestBody MvmStockDto mvmStockDto);

    @PostMapping(value = APP_ROOT+"/mvmStock/correctionneg")
    MvmStockDto correctionStockNeg (@RequestBody MvmStockDto mvmStockDto);

}
