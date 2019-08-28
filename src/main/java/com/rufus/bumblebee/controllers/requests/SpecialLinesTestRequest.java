package com.rufus.bumblebee.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecialLinesTestRequest extends BaseRequest {

    private Integer specialLen;
    private Integer increaseQuantity;
    private Boolean escSpecial;
    private Boolean special;

}
