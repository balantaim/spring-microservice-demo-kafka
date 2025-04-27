package com.martinatanasov.parent.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineDTO {

    @NotBlank
    @NonNull
    private UUID id;

    private String name;

    private Integer version;

    private Integer quantity;

    private WineType type;

    @NotNull
    private BigDecimal price;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
