package com.example.order_notification_service_fanout.producer;

import com.example.order_notification_service_fanout.dto.OrderEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderProducer {

    private final SnsTemplate snsTemplate;
    private final String topicName;

    public OrderProducer(
            SnsTemplate snsTemplate,
            @Value("${app.sns.order-events-topic}") String topicName) {
        this.snsTemplate = snsTemplate;
        this.topicName = topicName;
    }

    public void publishOrderEvent(OrderEvent orderEvent) {
        log.info("Publishing OrderEvent to SNS topic '{}': {}", topicName, orderEvent);
        snsTemplate.sendNotification(topicName, orderEvent, orderEvent.getEventType());
        log.info("Successfully published OrderEvent [orderId: {}] to SNS topic", orderEvent.getOrderId());
    }
}
