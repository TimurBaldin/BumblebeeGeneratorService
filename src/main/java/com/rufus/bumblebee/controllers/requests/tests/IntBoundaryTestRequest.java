package com.rufus.bumblebee.controllers.requests.tests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntBoundaryTestRequest extends BaseRequest {

    private Long boundaryIntEnd;
    private Long boundaryIntStart;
    private Integer quantity;


}
