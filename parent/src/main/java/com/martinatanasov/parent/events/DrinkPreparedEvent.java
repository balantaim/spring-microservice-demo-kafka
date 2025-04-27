package com.martinatanasov.parent.events;

import com.martinatanasov.parent.model.WineOrderLineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkPreparedEvent {

    private WineOrderLineDTO wineOrderLineDTO;
}