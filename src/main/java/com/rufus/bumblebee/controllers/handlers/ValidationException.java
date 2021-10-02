package com.rufus.bumblebee.controllers.handlers;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationException extends Exception{

    private List<ObjectError> errors;

    public ValidationException(String message) {
        super(message);
    }


    public ValidationException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

}
