package com.example.order_notification_service_fanout.service;

import com.example.order_notification_service_fanout.dto.OrderRequest;
import com.example.order_notification_service_fanout.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
