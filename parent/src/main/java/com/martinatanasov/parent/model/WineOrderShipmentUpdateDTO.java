package com.martinatanasov.parent.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WineOrderShipmentUpdateDTO {

    @NotBlank
    private String trackingNumber;

}
