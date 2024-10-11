package com.microservice.product_service.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSeviceCustomException extends RuntimeException{

    private String errorCode;

    public ProductSeviceCustomException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

}
