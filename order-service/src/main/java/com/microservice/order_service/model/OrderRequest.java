package com.microservice.order_service.model;

import com.microservice.order_service.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private long productId;
    private long quantity;
    private long totalAmount;
    private PaymentMode paymentMode;

}
