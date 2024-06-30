package com.seek.pruebatecnica.domain.exceptions;

public class BadRequestExceptionService extends RuntimeException {
    public BadRequestExceptionService(String message) {
        super(message);
    }

    public BadRequestExceptionService(ExceptionDetail exceptionDTO) {
        super(exceptionDTO.getJson());
    }
}