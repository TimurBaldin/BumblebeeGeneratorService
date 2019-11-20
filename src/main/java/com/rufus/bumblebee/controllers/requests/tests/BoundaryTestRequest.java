package com.rufus.bumblebee.controllers.requests.tests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoundaryTestRequest extends BaseRequest {

    private Integer len;
    private Integer increaseQuantity;
    private Boolean low;
    private Boolean cap;
    private Boolean nullValue;

}
