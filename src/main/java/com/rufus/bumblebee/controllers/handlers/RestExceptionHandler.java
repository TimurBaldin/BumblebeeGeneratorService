package com.rufus.bumblebee.controllers.handlers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    protected ResponseEntity<BaseResponse> handle(AppException ex) {
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(ex.getMessage());
        response.setDetailErrorMessage(ex.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
