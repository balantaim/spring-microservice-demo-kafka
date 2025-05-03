package com.martinatanasov.cold.listeners;


import com.martinatanasov.cold.config.KafkaConfig;
import com.martinatanasov.parent.events.DrinkPreparedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class DrinkPreparedListener {

    public AtomicInteger counter = new AtomicInteger(0);

    @KafkaListener(groupId = "cold-listener", topics = KafkaConfig.DRINK_PREPARED_TOPIC)
    public void listedDrinkRequest(DrinkPreparedEvent event) {

        log.info("Listening");
        counter.incrementAndGet();

    }

}
