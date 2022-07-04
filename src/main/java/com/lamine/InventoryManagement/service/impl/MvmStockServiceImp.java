package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.MvmStockDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.model.TypeMvtStk;
import com.lamine.InventoryManagement.repository.MvmStockRepository;
import com.lamine.InventoryManagement.service.ArticleService;
import com.lamine.InventoryManagement.service.MvmStockService;
import com.lamine.InventoryManagement.validator.MvmStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvmStockServiceImp implements MvmStockService {

    private MvmStockRepository mvmStockRepository;
    private ArticleService articleService ;
    @Autowired
    public MvmStockServiceImp(MvmStockRepository mvmStockRepository ,ArticleService articleService ) {
        this.mvmStockRepository = mvmStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {

        if (idArticle == null){
            log.warn("id article is null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvmStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvmStockDto> mvmStockArticle(Integer idArticle) {
        return mvmStockRepository.findAllByArticleId(idArticle).stream().map(MvmStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvmStockDto entreeStock(MvmStockDto mvmStockDto) {
        List<String> errors = MvmStockValidator.validate(mvmStockDto);
        if (!errors.isEmpty()){
            log.error("movement stock is not valid");
            throw new EntityInvalidException("movement stock is not valid", ErrorCode.MVM_STOCK_NOT_VALID,errors);
        }
        mvmStockDto.setQuantity(BigDecimal.valueOf(Math.abs(mvmStockDto.getQuantity().doubleValue())));
        mvmStockDto.setTypeMvt(TypeMvtStk.ENTREE);
        return MvmStockDto.fromEntity(mvmStockRepository.save(MvmStockDto.toEntity(mvmStockDto)));
    }

    @Override
    public MvmStockDto sortieStock(MvmStockDto mvmStockDto) {
        List<String> errors = MvmStockValidator.validate(mvmStockDto);
        if (!errors.isEmpty()){
            log.error("movement stock is not valid");
            throw new EntityInvalidException("movement stock is not valid", ErrorCode.MVM_STOCK_NOT_VALID,errors);
        }
        mvmStockDto.setQuantity(BigDecimal.valueOf(Math.abs(mvmStockDto.getQuantity().doubleValue())*-1));
        mvmStockDto.setTypeMvt(TypeMvtStk.SORTIE);
        return MvmStockDto.fromEntity(mvmStockRepository.save(MvmStockDto.toEntity(mvmStockDto)));
    }

    @Override
    public MvmStockDto correctionStockPos(MvmStockDto mvmStockDto) {
        List<String> errors = MvmStockValidator.validate(mvmStockDto);
        if (!errors.isEmpty()){
            log.error("movement stock is not valid");
            throw new EntityInvalidException("movement stock is not valid", ErrorCode.MVM_STOCK_NOT_VALID,errors);
        }
        mvmStockDto.setQuantity(BigDecimal.valueOf(Math.abs(mvmStockDto.getQuantity().doubleValue())));
        mvmStockDto.setTypeMvt(TypeMvtStk.CORRECTION_POS);
        return MvmStockDto.fromEntity(mvmStockRepository.save(MvmStockDto.toEntity(mvmStockDto)));
    }

    @Override
    public MvmStockDto correctionStockNeg(MvmStockDto mvmStockDto) {
        List<String> errors = MvmStockValidator.validate(mvmStockDto);
        if (!errors.isEmpty()){
            log.error("movement stock is not valid");
            throw new EntityInvalidException("movement stock is not valid", ErrorCode.MVM_STOCK_NOT_VALID,errors);
        }
        mvmStockDto.setQuantity(BigDecimal.valueOf(Math.abs(mvmStockDto.getQuantity().doubleValue())*-1));
        mvmStockDto.setTypeMvt(TypeMvtStk.CORRECTION_NEG);
        return MvmStockDto.fromEntity(mvmStockRepository.save(MvmStockDto.toEntity(mvmStockDto)));
    }
}
