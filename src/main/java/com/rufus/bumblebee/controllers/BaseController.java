package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;

public abstract class BaseController {

    public static final String TEST_RESULT = "TEST_SUCCESSFULLY";

    public BaseResponse getErrorResponse(Integer errorCode, String errorMessage, BaseResponse response) {
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    abstract String urlRequestTest();

}
