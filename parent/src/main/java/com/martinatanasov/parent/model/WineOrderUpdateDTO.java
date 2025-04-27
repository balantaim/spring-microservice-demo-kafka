package com.martinatanasov.parent.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineOrderUpdateDTO {

    private String customerRef;

    @NotNull
    private UUID customerId;

    private Set<WineOrderLineUpdateDTO> wineOrderLines;

    private WineOrderShipmentUpdateDTO wineOrderShipment;

    private BigDecimal paymentAmount;

}
