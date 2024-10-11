package com.microservice.product_service.exception;

import com.microservice.product_service.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerExceptionHandler{

    @ExceptionHandler(ProductSeviceCustomException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(
            ProductSeviceCustomException productNotFoundException){
        return new ResponseEntity<>
                (new ErrorResponse(productNotFoundException.getMessage(),
                        productNotFoundException.getErrorCode()),
                        HttpStatus.NOT_FOUND);
    }
}
