package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.FornisseurDto;

import java.util.List;

public interface FornisseurService {

    FornisseurDto getFornisseur (Integer id);
    List<FornisseurDto> getAllFornisseur ();
    FornisseurDto create (FornisseurDto fornisseurDto);
    void delete (Integer id);

}
