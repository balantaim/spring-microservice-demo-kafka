package com.martinatanasov.parent.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineOrderLineCreateDTO {

    @NotNull
    private UUID wineId;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer orderQuantity;

}
