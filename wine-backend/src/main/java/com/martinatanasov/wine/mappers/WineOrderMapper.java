package com.martinatanasov.wine.mappers;

import com.martinatanasov.parent.model.WineOrderDTO;
import com.martinatanasov.parent.model.WineOrderLineDTO;
import com.martinatanasov.parent.model.WineOrderShipmentDTO;
import com.martinatanasov.wine.entity.Order;
import com.martinatanasov.wine.entity.OrderLine;
import com.martinatanasov.wine.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WineOrderMapper {

    @Mapping(target = "order", ignore = true)
    Shipment wineOrderShipmentDtoToWineOrderShipment(WineOrderShipmentDTO wineOrderShipmentDTO);

    WineOrderShipmentDTO wineOrderShipmentToWineOrderShipmentDto(Shipment shipment);

    @Mapping(target = "order", ignore = true)
    OrderLine wineOrderLineDtoToWineOrderLine(WineOrderLineDTO wineOrderLineDTO);

    WineOrderLineDTO wineOrderLineToWineOrderLineDto(OrderLine orderLine);

    Order orderDtoToWineOrder(WineOrderDTO orderDTO);

    WineOrderDTO wineOrderToWineOrderDto(Order order);

}
