package com.martinatanasov.wine.mappers;

import com.martinatanasov.parent.model.WineDTO;
import com.martinatanasov.wine.entity.Audit;
import com.martinatanasov.wine.entity.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WineMapper {

//    @Mapping(target = "wine_category", ignore = true)
//    @Mapping(target = "wineOrderLines", ignore = true)
//    @Mapping(target = "category", ignore = true)
    Wine wineDtoToWine(WineDTO dto);

    WineDTO wineToWineDto(Wine wine);

    @Mapping(target = "createdDateAudit", ignore = true)
    @Mapping(target = "auditId", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    @Mapping(target = "customerName", ignore = true)
    Audit wineToWineAudit(Wine wine);
}
