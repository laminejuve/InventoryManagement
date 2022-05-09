package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.EntrepriseApi;
import com.lamine.InventoryManagement.dto.EntrepriseDto;
import com.lamine.InventoryManagement.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    EntrepriseService entrepriseService;
    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto getEntreprise(Integer id) {
        return entrepriseService.getEntreprise(id);
    }

    @Override
    public List<EntrepriseDto> getAllEntreprise() {
        return entrepriseService.getAllEntreprise();
    }

    @Override
    public EntrepriseDto create(EntrepriseDto entrepriseDto) {
        return entrepriseService.create(entrepriseDto);
    }

    @Override
    public void delete(Integer id) {
      entrepriseService.delete(id);
    }
}
