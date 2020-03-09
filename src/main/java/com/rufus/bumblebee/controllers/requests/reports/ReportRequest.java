package com.rufus.bumblebee.controllers.requests.reports;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReportRequest extends BaseReportRequest {

    @NotNull(message = "Не указано название отчета")
    private String docName;

}
