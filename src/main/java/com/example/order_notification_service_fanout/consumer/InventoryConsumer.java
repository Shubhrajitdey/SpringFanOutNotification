package com.example.order_notification_service_fanout.consumer;

import com.example.order_notification_service_fanout.dto.OrderEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryConsumer {

    @SqsListener("${app.queue.inventory-queue}")
    public void consumeInventoryEvent(OrderEvent event) {
        log.info("[Inventory Service] Received order event from SQS queue for orderId: {}", event.getOrderId());
        log.info("[Inventory Service] Updating stock for productId: {} by quantity: -{}", event.getProductId(), event.getQuantity());
        // Business logic for stock reduction/inventory updates
    }
}
