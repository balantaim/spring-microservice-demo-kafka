package com.martinatanasov.parent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineOrderDTO {

    private UUID id;

    private Long version;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    private String customerRef;

    private CustomerDTO customer;

    private BigDecimal paymentAmount;

    private Set<WineOrderLineDTO> wineOrderLines;

    private WineOrderShipmentDTO wineOrderShipment;

}
