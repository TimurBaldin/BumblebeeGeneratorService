package com.rufus.bumblebee.controllers.handlers;

import com.rufus.bumblebee.controllers.responses.BaseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<BaseErrorResponse> handle(Exception ex) {
        BaseErrorResponse response = new BaseErrorResponse();
        response.setErrorMessage(ex.getMessage());
        response.setDetailErrorMessage(ex.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
