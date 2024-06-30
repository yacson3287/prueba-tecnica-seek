package com.seek.pruebatecnica.domain.exceptions;

public class UnauthorizedExceptionService extends RuntimeException {

    public UnauthorizedExceptionService(String message) {
        super(message);
    }
}