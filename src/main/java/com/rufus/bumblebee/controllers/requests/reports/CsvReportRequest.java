package com.rufus.bumblebee.controllers.requests.reports;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CsvReportRequest extends ReportRequest {

    @NotNull(message = "Не указан разделитель")
    private String delimiter;

}
