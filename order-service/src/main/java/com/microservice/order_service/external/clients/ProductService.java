package com.microservice.order_service.external.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("reducequantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable long id,
            @RequestParam long quantity
    );
}
