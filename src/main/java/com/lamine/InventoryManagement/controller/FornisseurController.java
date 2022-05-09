package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.FornisseurApi;
import com.lamine.InventoryManagement.dto.FornisseurDto;
import com.lamine.InventoryManagement.service.FornisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FornisseurController implements FornisseurApi {

    FornisseurService fornisseurService;
    @Autowired
    public FornisseurController(FornisseurService fornisseurService) {
        this.fornisseurService = fornisseurService;
    }

    @Override
    public FornisseurDto getFronisseur(Integer id) {
        return fornisseurService.getFornisseur(id);
    }

    @Override
    public List<FornisseurDto> getAllFronisseur() {
        return fornisseurService.getAllFornisseur();
    }

    @Override
    public FornisseurDto create(FornisseurDto fornisseurDto) {
        return fornisseurService.create(fornisseurDto);
    }

    @Override
    public void delete(Integer id) {
       fornisseurService.delete(id);
    }
}
