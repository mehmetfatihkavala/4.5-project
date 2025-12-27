package com.turkcell.product_service.messaging.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCreatedConsumer {

    @Bean
    public Consumer<OrderCreatedEvent> orderCreater() {
        return event -> {
            System.out.println("Order created: " + event.productId());
        };
    }

    record OrderCreatedEvent(String productId) {
    }
}
