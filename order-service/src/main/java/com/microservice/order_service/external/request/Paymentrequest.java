package com.microservice.order_service.external.request;

import com.microservice.order_service.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paymentrequest {

    private long OrderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
