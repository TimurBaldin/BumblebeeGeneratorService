package com.rufus.bumblebee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:bumblebee_context.xml")
@EntityScan("com.rufus.bumblebee.tables")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
