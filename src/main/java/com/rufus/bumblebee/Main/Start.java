package com.rufus.bumblebee.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rufus.bumblebee.Main")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
