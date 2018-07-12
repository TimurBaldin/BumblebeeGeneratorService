package com.rufus.bumblebee.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages="com.rufus.bumblebee.Main")
public class Start  {
    public static void main(String[] args) {
     SpringApplication.run(Start.class, args);

    }


}
