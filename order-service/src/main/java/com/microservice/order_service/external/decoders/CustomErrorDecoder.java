package com.microservice.order_service.external.decoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.order_service.exception.CustomException;
import com.microservice.order_service.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("::{}",response.request().url());
        log.info("::{}",response.headers());

        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            throw new CustomException
                    (errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error","INTERNAL_SERVER_ERROR",500);
        }
    }
}
