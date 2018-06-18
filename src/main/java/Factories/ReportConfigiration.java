package Factories;
import Columns.ColumnLines;
import Reports.TestDataCSV;
import Reports.TestDataExcel;
import Rules.Report.ReportCSV;
import Rules.Report.ReportExcel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
public class ReportConfigiration {
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public ReportCSV getReportCSV(){
    return new TestDataCSV();
}
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public ReportExcel getReportExcel(){
    return new TestDataExcel();
}
}
