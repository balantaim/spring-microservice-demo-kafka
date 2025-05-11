package com.martinatanasov.wine.listeners;

import com.martinatanasov.parent.events.OrderPlacedEvent;
import com.martinatanasov.parent.model.WineOrderDTO;
import com.martinatanasov.wine.config.KafkaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Verify that messages are received by Kafka
 */

@SpringBootTest
@EmbeddedKafka(controlledShutdown = true, topics = {KafkaConfig.ORDER_PLACED_TOPIC}, partitions = 1, kraft = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OrderPlacedListenerTest {

    @Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    DrinkSplitterRouter drinkSplitter;

    @Autowired
    DrinkListenerKafkaConsumer drinkListenerKafkaConsumer;

    @Autowired
    OrderPlacedListener orderPlacedListener;

    @Autowired
    OrderPlacedKafkaListener orderPlacedKafkaListener;

    @BeforeEach
    void setUp() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(container -> {
            ContainerTestUtils.waitForAssignment(container, 1);
        });
    }

    @Test
    void listenSplitter()  {
        drinkSplitter.receive(OrderPlacedEvent.builder()
                .wineOrderDTO(WineOrderDTO.builder().build())
                .build());

        await().atMost(15, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(drinkListenerKafkaConsumer.iceColdMessageCounter::get, greaterThan(0));

        await().atMost(15, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(drinkListenerKafkaConsumer.coldMessageCounter::get, greaterThan(0));

        await().atMost(15, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(drinkListenerKafkaConsumer.coolMessageCounter::get, greaterThan(0));
    }

    @Test
    void listen() {
        OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.builder()
                .wineOrderDTO(WineOrderDTO.builder()
                        .id(UUID.randomUUID())
                        .build())
                .build();

        orderPlacedListener.listen(orderPlacedEvent);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            assertEquals(1, orderPlacedKafkaListener.messageCounter.get());
        });
    }

}