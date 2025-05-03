package com.martinatanasov.parent.model;


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
public class WineOrderShipmentDTO {

    private UUID id;

    private Long version;

    private String trackingNumber;

    private Timestamp createdDate;
    private Timestamp lastModifiedDate;

}
