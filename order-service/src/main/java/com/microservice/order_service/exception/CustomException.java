package com.microservice.order_service.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private String errorCode;
    private int errorStatus;

    public CustomException(String message,String errorCode,int errorStatus){
        super(message);
        this.errorCode=errorCode;
        this.errorStatus=errorStatus;
    }
}
