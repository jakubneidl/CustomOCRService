package com.example.customocrservice.exception;

import org.springframework.http.HttpStatus;

public class CustomOCRServiceException extends RuntimeException {

    private HttpStatus httpStatus;

    public CustomOCRServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
