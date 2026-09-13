package com.example.order_notification_service_fanout.consumer;

import com.example.order_notification_service_fanout.dto.OrderEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalyticsConsumer {

    @SqsListener("${app.queue.analytics-queue}")
    public void consumeAnalyticsEvent(OrderEvent event) {
        log.info("[Analytics Service] Received order event from SQS queue for orderId: {}", event.getOrderId());
        log.info("[Analytics Service] Recording metrics: totalAmount=${} for productId: {}", event.getTotalAmount(), event.getProductId());
        // Business logic for order metrics tracking & reporting
    }
}
