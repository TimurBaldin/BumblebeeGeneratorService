package com.rufus.bumblebee.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication(scanBasePackages="com.rufus.bumblebee.Main")
public class Start  {
    private static final String URL="http://localhost:8080/resources/static/index.html";
    public static void main(String[] args) {
     SpringApplication.run(Start.class, args);
        StartBrowser(URL);
        }
    public static void StartBrowser(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
