package com.rufus.bumblebee.services.exceptions;

public class AppException extends Exception {

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
