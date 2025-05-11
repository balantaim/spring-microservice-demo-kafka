package com.martinatanasov.wine.listeners;


import com.martinatanasov.parent.events.DrinkRequestEvent;
import com.martinatanasov.parent.events.OrderPlacedEvent;
import com.martinatanasov.parent.model.WineOrderLineDTO;
import com.martinatanasov.parent.model.WineType;
import com.martinatanasov.wine.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DrinkSplitterRouter {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(groupId = "DrinkSplitterRouter", topics = KafkaConfig.ORDER_PLACED_TOPIC)
    public void receive(@Payload OrderPlacedEvent orderPlacedEvent) {

        if (orderPlacedEvent.getWineOrderDTO() == null ||
                orderPlacedEvent.getWineOrderDTO().getWineOrderLines() == null ||
                orderPlacedEvent.getWineOrderDTO().getWineOrderLines().isEmpty()) {
            log.error("Invalid Order Placed Event");
            return;
        }

        orderPlacedEvent.getWineOrderDTO().getWineOrderLines().forEach(wineOrderLine -> {
            switch (wineOrderLine.getWineDTO().getType()) {
                case WineType.RED -> {
                    log.debug("Splitting red wine Order");
                    sendCoolWine(wineOrderLine);
                }
                case WineType.WHITE -> {
                    log.debug("Splitting white wine Order");
                    sendIceColdWine(wineOrderLine);
                }
                case WineType.ROSE -> {
                    log.debug("Splitting rose wine Order");
                    sendColdWine(wineOrderLine);
                }
                default -> log.error("Unknown wine");
            }
        });
    }

    private void sendIceColdWine(WineOrderLineDTO wineOrderLineDTO) {
        //Send ice cold wine
        kafkaTemplate.send(KafkaConfig.DRINK_REQUEST_ICE_COLD_TOPIC, DrinkRequestEvent.builder()
                .wineOrderLineDTO(wineOrderLineDTO)
                .build());
    }

    private void sendColdWine(WineOrderLineDTO wineOrderLineDTO) {
        //Send cold wine
        kafkaTemplate.send(KafkaConfig.DRINK_REQUEST_COLD_TOPIC, DrinkRequestEvent.builder()
                .wineOrderLineDTO(wineOrderLineDTO)
                .build());
    }

    private void sendCoolWine(WineOrderLineDTO wineOrderLineDTO) {
        //Send cool wine
        kafkaTemplate.send(KafkaConfig.DRINK_REQUEST_COOL_TOPIC, DrinkRequestEvent.builder()
                .wineOrderLineDTO(wineOrderLineDTO)
                .build());
    }

}
