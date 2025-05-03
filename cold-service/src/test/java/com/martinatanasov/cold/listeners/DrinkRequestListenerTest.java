package com.martinatanasov.cold.listeners;

import com.martinatanasov.cold.config.KafkaConfig;
import com.martinatanasov.parent.events.DrinkRequestEvent;
import com.martinatanasov.parent.model.WineDTO;
import com.martinatanasov.parent.model.WineOrderLineDTO;
import com.martinatanasov.parent.model.WineType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@EmbeddedKafka(controlledShutdown = true,
        topics = {KafkaConfig.DRINK_PREPARED_TOPIC, KafkaConfig.DRINK_REQUEST_COLD_TOPIC},
        partitions = 1, kraft = true)
@SpringBootTest
class DrinkRequestListenerTest {

    @Autowired
    DrinkRequestListener drinkRequestListener;

    @Autowired
    DrinkPreparedListener drinkPreparedListener;

    @Test
    void listedDrinkRequest() {
        drinkRequestListener.listedDrinkRequest(DrinkRequestEvent.builder()
                .wineOrderLineDTO(getTestWine())
                .build());

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            assertEquals(1, drinkPreparedListener.counter.get());
        });

    }


    public WineOrderLineDTO getTestWine() {
        return WineOrderLineDTO.builder()
                .wineDTO(
                        WineDTO.builder()
                                .id(UUID.randomUUID())
                                .name("Chardonnay Sauvignon")
                                .price(new BigDecimal("45.00"))
                                .quantity(1)
                                .type(WineType.RED)
                                .build()
                )
                .build();
    }
}