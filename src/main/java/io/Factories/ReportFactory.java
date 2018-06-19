package io.Factories;

import io.Reports.TestDataCSV;
import io.Reports.TestDataExcel;
import io.Rules.Report.ReportCSV;
import io.Rules.Report.ReportExcel;

public class ReportFactory {
    public ReportCSV getReportCSV() {
        return new TestDataCSV();
    }

    public ReportExcel getReportExcel() {
        return new TestDataExcel();
    }
}
