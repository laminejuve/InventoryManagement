package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.MvmStockApi;
import com.lamine.InventoryManagement.dto.MvmStockDto;
import com.lamine.InventoryManagement.service.MvmStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvmStockController implements MvmStockApi {

    MvmStockService mvmStockService;
    @Autowired
    public MvmStockController(MvmStockService mvmStockService) {
        this.mvmStockService = mvmStockService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mvmStockService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvmStockDto> mvmStockArticle(Integer idArticle) {
        return mvmStockService.mvmStockArticle(idArticle);
    }

    @Override
    public MvmStockDto entreeStock(MvmStockDto mvmStockDto) {
        return mvmStockService.entreeStock(mvmStockDto);
    }

    @Override
    public MvmStockDto sortieStock(MvmStockDto mvmStockDto) {
        return mvmStockService.sortieStock(mvmStockDto);
    }

    @Override
    public MvmStockDto correctionStockPos(MvmStockDto mvmStockDto) {
        return mvmStockService.correctionStockPos(mvmStockDto);
    }

    @Override
    public MvmStockDto correctionStockNeg(MvmStockDto mvmStockDto) {
        return mvmStockService.correctionStockNeg(mvmStockDto);
    }
}
