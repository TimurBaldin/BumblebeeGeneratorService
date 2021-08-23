package com.rufus.bumblebee.controllers.requests;

public class ContainerRequest {
    private String name;
    private Boolean historyOn;
    private ReportType reportType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public Boolean getHistoryOn() {
        return historyOn;
    }

    public void setHistoryOn(Boolean historyOn) {
        this.historyOn = historyOn;
    }
}
