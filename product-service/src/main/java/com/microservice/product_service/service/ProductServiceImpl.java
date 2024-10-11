package com.microservice.product_service.service;

import com.microservice.product_service.entity.Product;
import com.microservice.product_service.exception.ProductSeviceCustomException;
import com.microservice.product_service.model.ProductRequest;
import com.microservice.product_service.model.ProductResponse;
import com.microservice.product_service.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product");
        Product product = new Product(productRequest.getProductName(),
                productRequest.getProductPrice(),
                productRequest.getQuantity());
        System.out.println(productRequest.getProductName());
        System.out.println(productRequest.getProductPrice());
        System.out.println(productRequest.getQuantity());

        Product savedProduct = productRepository.save(product);
        log.info("Added Product");
        return savedProduct.getProductId();
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductSeviceCustomException("Product is not found with id "+id,"PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceProductQuantity(long id, long quantity) {
        log.info("Reduce product quantity {} with id {}",quantity,id);
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ProductSeviceCustomException("Product Not Found with id "+id,
                        "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductSeviceCustomException
                    ("Product doesn't have the required quantity","INSUFFICIENT_PRODUCT_QUANTITY");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product updated successfully");
    }
}
