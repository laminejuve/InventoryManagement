package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto getEntreprise (Integer id);
    List<EntrepriseDto> getAllEntreprise ();
    EntrepriseDto create (EntrepriseDto entrepriseDto);
    void delete (Integer id);

}
