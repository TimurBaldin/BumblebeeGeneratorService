package com.rufus.bumblebee.Main.Config;

import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Rules.BaseFactory;
import com.rufus.bumblebee.Main.Rules.BaseService;
import com.rufus.bumblebee.Main.Rules.DAO.BaseRepository;
import com.rufus.bumblebee.Main.Rules.Report.ReportCSV;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;
import com.rufus.bumblebee.Main.Services.LinesService;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReportService getReportService() {
        return new ReportService(getFactoryReport());
    }
    @Bean
   @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public ReportFactory getFactoryReport(){
       return new ReportFactory();
   }
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public TestsFactory geTestsFactory(){
        return new TestsFactory();
   }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RepositiryTestValues getRepositiry() {
        return new RepositiryTestValues();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LinesService linesService() {
        return new LinesService(getReportService(), getRepositiry(),geTestsFactory());
    }
}
