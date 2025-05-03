package com.martinatanasov.parent.model;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineOrderLineDTO {

    private UUID id;

    private Long version;

    private WineOrderLineStatus wineOrderLineStatus;

    private WineDTO wineDTO;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer orderQuantity;

    private Integer quantityAllocated;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

}
