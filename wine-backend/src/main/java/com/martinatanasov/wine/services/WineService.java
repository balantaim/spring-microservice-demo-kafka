package com.martinatanasov.wine.services;

import com.martinatanasov.parent.model.WineDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface WineService {

    Page<WineDTO> listWines(String wineName, Integer pageNumber, Integer pageSize);

    Optional<WineDTO> getWineById(UUID id);

    WineDTO saveNewWine(WineDTO wineDTO);

    Optional<WineDTO> updateWineById(UUID wineId, WineDTO wineDTO);

    Boolean deleteById(UUID wineId);

    Optional<WineDTO> patchWineById(UUID wineId, WineDTO wineDTO);

}
