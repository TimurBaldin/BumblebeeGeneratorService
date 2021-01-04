package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.responses.BaseResponse;

public abstract class BaseController {

    public BaseResponse sendErrorResponse(Integer errorCode, String errorMessage, BaseResponse response) {
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }
}
