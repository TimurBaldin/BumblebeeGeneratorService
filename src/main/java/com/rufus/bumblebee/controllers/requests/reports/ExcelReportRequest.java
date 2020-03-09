package com.rufus.bumblebee.controllers.requests.reports;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ExcelReportRequest extends ReportRequest {

    @NotNull(message = "Не указан sheet")
    private String sheetName;

}
