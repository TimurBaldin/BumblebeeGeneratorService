package com.rufus.bumblebee.Main.Config;


import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Services.LinesService;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReportService getReportService() {
        return new ReportService(getFactoryReport());
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReportFactory getFactoryReport() {
        return new ReportFactory();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TestsFactory geTestsFactory() {
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
        return new LinesService(getReportService(), getRepositiry(), geTestsFactory());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}
