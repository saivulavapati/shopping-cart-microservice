package com.microservice.product_service.service;

import com.microservice.product_service.model.ProductRequest;
import com.microservice.product_service.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long id);

    void reduceProductQuantity(long id, long quantity);
}
