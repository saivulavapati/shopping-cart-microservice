package com.microservice.product_service.controller;

import com.microservice.product_service.model.ProductRequest;
import com.microservice.product_service.model.ProductResponse;
import com.microservice.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequest productRequest){
        System.out.println(productRequest);
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable long id){
        ProductResponse productById = productService.getProductById(id);
        return new ResponseEntity<>(productById,HttpStatus.OK);

    }

    @PutMapping("reducequantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable long id,
            @RequestParam long quantity
    ){
       productService.reduceProductQuantity(id,quantity);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
