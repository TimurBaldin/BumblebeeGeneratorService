package com.rufus.bumblebee.Main;

import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Services.LinesService;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.rufus.bumblebee.Main")
public class Start {
    public static void main(String[] args) {
       SpringApplication.run(Start.class, args);

    }

}
