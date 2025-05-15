package com.martinatanasov.wine.listeners;

import com.martinatanasov.parent.events.DrinkPreparedEvent;
import com.martinatanasov.parent.model.WineOrderLineStatus;
import com.martinatanasov.wine.config.KafkaConfig;
import com.martinatanasov.wine.repositories.WineOrderLineRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DrinkPreparedListener {

    private final WineOrderLineRepository wineOrderLineRepository;

    @Transactional
    @KafkaListener(groupId = "DrinkPreparedListener", topics = KafkaConfig.DRINK_PREPARED_TOPIC)
    public void listen(DrinkPreparedEvent event) {
        wineOrderLineRepository.findById(event.getWineOrderLineDTO().getId())
                .ifPresentOrElse(wineOrderLine -> {
                            wineOrderLine.setOrderLineStatus(WineOrderLineStatus.COMPLETE);
                            wineOrderLineRepository.save(wineOrderLine);
                        },
                        () -> log.error("Wine order line not found!")
                );
    }

}

