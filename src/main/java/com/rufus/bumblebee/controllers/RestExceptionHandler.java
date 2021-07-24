package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ValidationException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(
            value = {NotFoundException.class, ValidationException.class}
    )
    protected ResponseEntity<Object> handle(Exception ex) {
        BaseResponse response = new BaseResponse();
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
