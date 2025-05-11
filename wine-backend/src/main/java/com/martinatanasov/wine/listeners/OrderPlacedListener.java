package com.martinatanasov.wine.listeners;

import com.martinatanasov.parent.events.OrderPlacedEvent;
import com.martinatanasov.wine.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPlacedListener {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    @EventListener
    public void listen(OrderPlacedEvent event) {

        log.debug("Order placed");
        kafkaTemplate.send(KafkaConfig.ORDER_PLACED_TOPIC, event);

    }

}
