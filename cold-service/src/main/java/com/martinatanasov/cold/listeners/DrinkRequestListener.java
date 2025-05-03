package com.martinatanasov.cold.listeners;

import com.martinatanasov.cold.config.KafkaConfig;
import com.martinatanasov.cold.services.DrinkRequestProcessor;
import com.martinatanasov.parent.events.DrinkPreparedEvent;
import com.martinatanasov.parent.events.DrinkRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DrinkRequestListener {

    private final DrinkRequestProcessor drinkRequestProcessor;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(groupId = "ColdListener", topics = KafkaConfig.DRINK_REQUEST_COLD_TOPIC)
    public void listedDrinkRequest(DrinkRequestEvent event) {
        log.debug("Drink request event");

        drinkRequestProcessor.processDrinkRequest(event);

        kafkaTemplate.send(KafkaConfig.DRINK_PREPARED_TOPIC, DrinkPreparedEvent.builder()
                .wineOrderLineDTO(event.getWineOrderLineDTO())
                .build());
    }

}
