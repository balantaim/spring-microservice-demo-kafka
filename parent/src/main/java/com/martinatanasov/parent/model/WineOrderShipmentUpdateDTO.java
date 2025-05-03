package com.martinatanasov.parent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineOrderShipmentUpdateDTO {

    private String trackingNumber;

}
