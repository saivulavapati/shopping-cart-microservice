package com.microservice.payment_service.service;

import com.microservice.payment_service.model.PaymentResponse;
import com.microservice.payment_service.model.Paymentrequest;

public interface PaymentService {
    long doPayment(Paymentrequest paymentrequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);

}
