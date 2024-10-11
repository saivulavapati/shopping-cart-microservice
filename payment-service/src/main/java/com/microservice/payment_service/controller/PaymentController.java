package com.microservice.payment_service.controller;

import com.microservice.payment_service.model.PaymentResponse;
import com.microservice.payment_service.model.Paymentrequest;
import com.microservice.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody Paymentrequest paymentrequest){
        long id = paymentService.doPayment(paymentrequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable("id") long orderId){
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }
}
