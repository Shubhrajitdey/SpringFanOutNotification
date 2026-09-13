package com.example.order_notification_service_fanout.service;

import com.example.order_notification_service_fanout.dto.OrderEvent;
import com.example.order_notification_service_fanout.dto.OrderRequest;
import com.example.order_notification_service_fanout.dto.OrderResponse;
import com.example.order_notification_service_fanout.exception.InvalidOrderException;
import com.example.order_notification_service_fanout.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            throw new InvalidOrderException("Order request cannot be null");
        }
        if (orderRequest.getProductId() == null || orderRequest.getProductId().isBlank()) {
            throw new InvalidOrderException("Product ID is required");
        }
        if (orderRequest.getQuantity() == null || orderRequest.getQuantity() <= 0) {
            throw new InvalidOrderException("Quantity must be greater than zero");
        }
        if (orderRequest.getPrice() == null || orderRequest.getPrice() <= 0) {
            throw new InvalidOrderException("Price must be greater than zero");
        }

        String orderId = (orderRequest.getOrderId() != null && !orderRequest.getOrderId().isBlank())
                ? orderRequest.getOrderId()
                : UUID.randomUUID().toString();

        double totalAmount = orderRequest.getQuantity() * orderRequest.getPrice();

        OrderEvent orderEvent = OrderEvent.builder()
                .orderId(orderId)
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .totalAmount(totalAmount)
                .customerEmail(orderRequest.getCustomerEmail())
                .eventType("ORDER_CREATED")
                .eventTimestamp(LocalDateTime.now())
                .build();

        log.info("Processing order creation for orderId: {}", orderId);
        orderProducer.publishOrderEvent(orderEvent);

        return OrderResponse.builder()
                .orderId(orderId)
                .status("SUCCESS")
                .message("Order created and event published to SNS successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
