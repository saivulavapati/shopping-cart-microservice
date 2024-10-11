package com.microservice.order_service.service;

import com.microservice.order_service.model.OrderRequest;
import com.microservice.order_service.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long id);
}
