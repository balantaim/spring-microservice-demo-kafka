package com.martinatanasov.wine.events;

import com.martinatanasov.wine.entity.Wine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class WineDeletedEvent implements WineEvent {

    private Wine wine;

    private String customerName;

}
