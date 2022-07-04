package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.MvmStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvmStockService {

    BigDecimal stockReelArticle (Integer idArticle);
    List<MvmStockDto> mvmStockArticle (Integer idArticle);
    MvmStockDto entreeStock (MvmStockDto mvmStockDto);
    MvmStockDto sortieStock (MvmStockDto mvmStockDto);
    MvmStockDto correctionStockPos (MvmStockDto mvmStockDto);
    MvmStockDto correctionStockNeg (MvmStockDto mvmStockDto);

}
