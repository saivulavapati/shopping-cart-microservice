package com.microservice.payment_service.repository;

import com.microservice.payment_service.entity.TranscationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TranscationDetails,Long> {

    TranscationDetails findByOrderId(long orderId);
}
