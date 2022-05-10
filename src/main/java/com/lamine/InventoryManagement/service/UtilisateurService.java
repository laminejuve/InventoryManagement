package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto getUtilisateur (Integer id);
    List<UtilisateurDto> getAllUtilisateurs ();
    UtilisateurDto create (UtilisateurDto utilisateurDto);
    void delete (Integer id);
    
}
