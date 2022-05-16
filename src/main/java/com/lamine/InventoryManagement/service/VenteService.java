package com.lamine.InventoryManagement.service;

import com.lamine.InventoryManagement.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto getVente (Integer id);
    List<VenteDto> getAllVente ();
    VenteDto create (VenteDto venteDto);
    void delete (Integer id);

}
