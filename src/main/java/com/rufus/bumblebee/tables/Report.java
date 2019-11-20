package com.rufus.bumblebee.tables;

import com.rufus.bumblebee.configurer.enums.ReportStatus;
import com.rufus.bumblebee.configurer.enums.ReportType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REPORTS", schema = "REPOSITORY")
@Getter
@Setter
@ToString
public class Report {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @SequenceGenerator(name = "report_id", sequenceName = "REPOSITORY.report_id", allocationSize = 1)
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

}
