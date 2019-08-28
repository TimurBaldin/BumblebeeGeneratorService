package com.rufus.bumblebee.controllers.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntRangeTestRequest extends BaseRequest {

    private Long maxIntVal;
    private Long minIntVal;

}
