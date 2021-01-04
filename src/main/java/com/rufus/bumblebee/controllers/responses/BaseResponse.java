package com.rufus.bumblebee.controllers.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponse<V> {

    private Integer errorCode = 0;
    private String errorMessage;
    private V response;

}
