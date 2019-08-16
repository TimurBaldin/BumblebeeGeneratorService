package com.rufus.bumblebee.controllers.Responses;

public class BaseResponse<V> {

    private Integer errorCode = 0;
    private String errorMessage;
    private V response;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public V getResponse() {
        return response;
    }

    public void setResponse(V response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", response=" + response +
                '}';
    }

}
