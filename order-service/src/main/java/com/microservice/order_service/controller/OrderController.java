package com.microservice.order_service.controller;

import com.microservice.order_service.entity.Order;
import com.microservice.order_service.model.OrderRequest;
import com.microservice.order_service.model.OrderResponse;
import com.microservice.order_service.service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("id") long orderId){
        OrderResponse orderDetails = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
    }
}
