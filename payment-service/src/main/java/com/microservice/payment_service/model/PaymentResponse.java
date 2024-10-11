package com.microservice.payment_service.model;

import com.microservice.payment_service.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private long orderId;
    private long amount;
    private String paymentMode;
    private Instant paymentDate;
    private String paymentStatus;
}
