package com.rufus.bumblebee.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
class BaseRequest {

    @NotNull
    private Long containerId;

}
