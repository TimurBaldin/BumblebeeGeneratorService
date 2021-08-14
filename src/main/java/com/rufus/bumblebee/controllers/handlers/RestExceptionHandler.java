package com.rufus.bumblebee.controllers.handlers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.exceptions.GeneratorCreatingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(
            value = {NoResultException.class, ValidationException.class, GeneratorCreatingException.class}
    )
    protected ResponseEntity<Object> handle(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
