package com.rufus.bumblebee.Main.Config;

import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
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
    public ReportService getReportService(){
        return new ReportService();
    }
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public RepositiryTestValues getRepositiry() {
    return new RepositiryTestValues();
}
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public LinesService linesService() {
    return new LinesService(getReportService(),getRepositiry());
}
}
