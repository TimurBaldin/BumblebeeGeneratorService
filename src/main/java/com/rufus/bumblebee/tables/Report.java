package com.rufus.bumblebee.tables;

import com.rufus.bumblebee.utils.enums.ReportStatus;
import com.rufus.bumblebee.utils.enums.ReportType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REPORTS", schema = "REPOSITORY")
public class Report {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "report_id", sequenceName = "repository.report_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_id")
    private Long id;

    @Column(name = "REPORT_TYPE")
    private ReportType reportType;

    @Column(name = "STATUS")
    private ReportStatus reportStatus;

    @Column(name = "CREATION_DATE")
    private Date date;

    @Column(name = "CONTAINER_REF")
    private Long containerRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getContainerRef() {
        return containerRef;
    }

    public void setContainerRef(Long containerRef) {
        this.containerRef = containerRef;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportType=" + reportType +
                ", reportStatus=" + reportStatus +
                ", date=" + date +
                ", containerRef=" + containerRef +
                '}';
    }

}
