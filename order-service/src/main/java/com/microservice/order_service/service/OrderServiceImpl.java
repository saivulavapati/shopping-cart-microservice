package com.microservice.order_service.service;

import com.microservice.order_service.entity.Order;
import com.microservice.order_service.exception.CustomException;
import com.microservice.order_service.external.clients.PaymentService;
import com.microservice.order_service.external.clients.ProductService;
import com.microservice.order_service.external.request.Paymentrequest;
import com.microservice.order_service.model.OrderRequest;
import com.microservice.order_service.model.OrderResponse;
import com.microservice.order_service.model.PaymentResponse;
import com.microservice.order_service.model.ProductResponse;
import com.microservice.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Creating order {}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating order with status CREATED");

        Order order = new Order();
        order.setProductId(orderRequest.getProductId());
        order.setOrderStatus("CREATED");
        order.setQuantity(orderRequest.getQuantity());
        order.setAmount(orderRequest.getTotalAmount());
        order.setOrderDate(Instant.now());

        Order savedOrder = orderRepository.save(order);
        log.info("Order Placed {}",savedOrder);

        log.info("Calling Payment service,to make payment");
        Paymentrequest paymentrequest = new Paymentrequest();
        paymentrequest.setOrderId(order.getOrderId());
        paymentrequest.setAmount(orderRequest.getTotalAmount());
        paymentrequest.setPaymentMode(orderRequest.getPaymentMode());
        String orderStatus = null;
        try{
            paymentService.doPayment(paymentrequest);
            log.info("Payment successfull, Changing the order status to PLACED");
            orderStatus="PLACED";
        }catch (Exception exception){
            log.error("Payment failed, Changing the order status to PAYMENT_FAILED");
            orderStatus="PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        log.info("Changing the order status after calling payment-service");
        orderRepository.save(order);
        return savedOrder.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(long id) {
        log.info("Getting order details with id ",id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Order Not found with id " + id, "NOT_FOUND", 404));
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setAmount(order.getAmount());
        orderResponse.setOrderStatus(order.getOrderStatus());

        log.info("Calling Product service to get Product Details");
        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);
        orderResponse.setProductDetails(productResponse);

        log.info("Calling payment-service to get payment Details");
        PaymentResponse paymentResponse =restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/"+order.getOrderId(),PaymentResponse.class);
        orderResponse.setPaymentDetails(paymentResponse);
        return orderResponse;
    }
}
