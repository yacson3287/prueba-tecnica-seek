package com.seek.pruebatecnica.infrastructure.exceptions;

import com.seek.pruebatecnica.domain.exceptions.BadRequestExceptionService;
import com.seek.pruebatecnica.domain.exceptions.ExceptionDetail;
import com.seek.pruebatecnica.domain.exceptions.InternalServerErrorExceptionService;
import com.seek.pruebatecnica.domain.exceptions.UnauthorizedExceptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    public static final String OPERATION_NOT_COMPLETED = "It was not possible execute the action";

    //Controllers
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerBadRequestExceptionController(MethodArgumentNotValidException exception) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(OPERATION_NOT_COMPLETED);
        exception.getBindingResult().getAllErrors().forEach(error -> {
                    String key = ((FieldError) error).getField();
                    String value = error.getDefaultMessage();
                    exceptionDetail.addDetail(key, value);
                }
        );

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exceptionDetail.getJson());
    }

    //General
    @ExceptionHandler(value = {BadRequestExceptionService.class})
    public ProblemDetail handlerBadRequestException(BadRequestExceptionService e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = {InternalServerErrorExceptionService.class})
    public ProblemDetail handlerInternalServerErrorException(InternalServerErrorExceptionService e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = {UnauthorizedExceptionService.class})
    public ProblemDetail handlerUnauthorizedException(UnauthorizedExceptionService e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
}
