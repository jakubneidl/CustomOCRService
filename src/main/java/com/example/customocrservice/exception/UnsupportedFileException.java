package com.example.customocrservice.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedFileException extends CustomOCRServiceException {

    private static final String MESSAGE = "File not supported";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public UnsupportedFileException() {
        super(MESSAGE, HTTP_STATUS);
    }

    public UnsupportedFileException(String message) {
        super(message, HTTP_STATUS);
    }
}
