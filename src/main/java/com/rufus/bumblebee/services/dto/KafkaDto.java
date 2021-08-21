package com.rufus.bumblebee.services.dto;

import com.rufus.bumblebee.controllers.requests.ReportType;

import java.util.List;

public class KafkaDto {

    private String cuid;
    private String containerName;
    private Boolean isAuthenticated;
    private ReportType reportType;
    private List<TestDataDto> data;

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public List<TestDataDto> getData() {
        return data;
    }

    public void setData(List<TestDataDto> data) {
        this.data = data;
    }
}
