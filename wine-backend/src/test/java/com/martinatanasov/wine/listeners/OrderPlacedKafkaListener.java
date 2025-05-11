package com.martinatanasov.wine.listeners;


import com.martinatanasov.parent.events.OrderPlacedEvent;
import com.martinatanasov.wine.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class OrderPlacedKafkaListener {

    AtomicInteger messageCounter = new AtomicInteger(0);

    @KafkaListener(groupId = "KafkaIntegrationTest", topics = KafkaConfig.ORDER_PLACED_TOPIC)
    public void receive(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received message: {}", orderPlacedEvent);
        messageCounter.incrementAndGet();
    }
}
