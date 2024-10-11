package com.microservice.payment_service.service;

import com.microservice.payment_service.entity.TranscationDetails;
import com.microservice.payment_service.model.PaymentResponse;
import com.microservice.payment_service.model.Paymentrequest;
import com.microservice.payment_service.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public long doPayment(Paymentrequest paymentrequest) {

        log.info("Recording payment details {}",paymentrequest);

        TranscationDetails transcationDetails = new TranscationDetails();
        transcationDetails.setPaymentDate(Instant.now());
        transcationDetails.setPaymentMode(paymentrequest.getPaymentMode().name());
        transcationDetails.setPaymentStatus("SUCCESS");
        transcationDetails.setAmount(paymentrequest.getAmount());
        transcationDetails.setOrderId(paymentrequest.getOrderId());
        transcationDetails.setReferenceNumber(paymentrequest.getReferenceNumber());

        TranscationDetails savedTranscation = paymentRepository.save(transcationDetails);

        log.info("Recorded transcation details with id {}",savedTranscation.getId());
        return savedTranscation.getId();

    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting Transcation Details with order id {}",orderId);

        TranscationDetails transcationDetails = paymentRepository.findByOrderId(orderId);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentDate(transcationDetails.getPaymentDate());
        paymentResponse.setPaymentMode(transcationDetails.getPaymentMode());
        paymentResponse.setPaymentStatus(transcationDetails.getPaymentStatus());
        paymentResponse.setOrderId(transcationDetails.getOrderId());
        paymentResponse.setAmount(transcationDetails.getAmount());

        log.info("Transcation Details are {}",paymentResponse);
        return paymentResponse;

    }
}
