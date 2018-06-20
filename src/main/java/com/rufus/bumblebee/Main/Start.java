package com.rufus.bumblebee.Main;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Services.LinesService;
import com.rufus.bumblebee.Main.Services.ReportService;
import com.rufus.bumblebee.Main.Tools.SessionUntil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.rufus.bumblebee.Main")
public class Start {
    public static void main(String[] args) {
      SpringApplication.run(Start.class, args);



    }
}
