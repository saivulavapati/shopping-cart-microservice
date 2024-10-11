package com.microservice.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long orderId;
    private String orderStatus;
    private long amount;
    private Instant orderDate;
    private ProductResponse productDetails;
    private PaymentResponse paymentDetails;

}
