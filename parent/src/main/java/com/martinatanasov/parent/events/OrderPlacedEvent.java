package com.martinatanasov.parent.events;

import com.martinatanasov.parent.model.WineOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

    private WineOrderDTO wineOrderDTO;

}
