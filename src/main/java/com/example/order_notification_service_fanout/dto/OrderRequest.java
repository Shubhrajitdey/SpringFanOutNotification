package com.example.order_notification_service_fanout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private String customerEmail;
}
