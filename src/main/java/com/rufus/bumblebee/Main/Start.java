package com.rufus.bumblebee.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:bumblebee_context.xml")
public class Start {
    public static void main(String[] args) {
       SpringApplication.run(Start.class, args);

    }

}
