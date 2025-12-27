package com.turkcell.order_service.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final StreamBridge streamBridge;

    public OrdersController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping
    public String createOrder(@RequestBody CreateOrderDto createOrderDto) {
        OrderCreatedEvent event = new OrderCreatedEvent(createOrderDto.productId());

        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(event).build();

        streamBridge.send("orderCreated-out-0", message);
        return createOrderDto.productId();
    }

    public record CreateOrderDto(String productId) {
    }

    public record OrderCreatedEvent(String productId) {
    }
}
