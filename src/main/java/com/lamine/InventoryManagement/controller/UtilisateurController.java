package com.lamine.InventoryManagement.controller;

import com.lamine.InventoryManagement.controller.api.UtilisateurApi;
import com.lamine.InventoryManagement.dto.UtilisateurDto;
import com.lamine.InventoryManagement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    UtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto getUtilisateur(Integer id) {
        return utilisateurService.getUtilisateur(id);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @Override
    public UtilisateurDto create(UtilisateurDto utilisateurDto) {
        return utilisateurService.create(utilisateurDto);
    }

    @Override
    public void delete(Integer id) {
      utilisateurService.delete(id);
    }
}
