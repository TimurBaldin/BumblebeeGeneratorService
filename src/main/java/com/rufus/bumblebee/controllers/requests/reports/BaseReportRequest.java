package com.rufus.bumblebee.controllers.requests.reports;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BaseReportRequest {

    @NotNull(message = "Не передан список containerId")
    private List<Long> containerId;

}
