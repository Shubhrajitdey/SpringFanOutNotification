package com.example.order_notification_service_fanout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent {
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private Double totalAmount;
    private String customerEmail;
    private String eventType;
    private LocalDateTime eventTimestamp;
}
