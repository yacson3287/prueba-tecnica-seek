package com.seek.pruebatecnica.domain.exceptions;

public class InternalServerErrorExceptionService extends RuntimeException {

    public InternalServerErrorExceptionService(String message) {
        super(message);
    }
}