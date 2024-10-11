package com.microservice.order_service.exception;

import com.microservice.order_service.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(
            CustomException customException){
        return new ResponseEntity<>
                (new ErrorResponse(customException.getMessage(),
                        customException.getErrorCode()),
                        HttpStatus.valueOf(customException.getErrorStatus()));
    }
}
