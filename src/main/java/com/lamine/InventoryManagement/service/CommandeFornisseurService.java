package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.CommandeFornisseurDto;

import java.util.List;

public interface CommandeFornisseurService {

    CommandeFornisseurDto getCmdFornisseur(Integer id);
    List<CommandeFornisseurDto> getAllCmdFornisseur();
    CommandeFornisseurDto create (CommandeFornisseurDto commandeFornisseurDto);
    void delete (Integer id);

}
