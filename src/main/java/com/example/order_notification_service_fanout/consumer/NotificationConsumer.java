package com.example.order_notification_service_fanout.consumer;

import com.example.order_notification_service_fanout.dto.OrderEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @SqsListener("${app.queue.notification-queue}")
    public void consumeNotificationEvent(OrderEvent event) {
        log.info("[Notification Service] Received order event from SQS queue for orderId: {}", event.getOrderId());
        log.info("[Notification Service] Sending order confirmation email to: {}", event.getCustomerEmail());
        // Business logic for sending email/SMS notification
    }
}
