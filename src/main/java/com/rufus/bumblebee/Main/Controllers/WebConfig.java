package com.rufus.bumblebee.Main.Controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/static/indexstyle.css").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/static/script.js").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/static/index.html").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/static/suite.css").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/static/testsuitehandler.js").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");

        registry.addResourceHandler("/resources/templates/about.html").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/templates/generator.html").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/templates/howuse.html").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");
        registry.addResourceHandler("/resources/templates/testsuite.html").addResourceLocations("file:///C:/Users/Timur/Documents/Data Generator/src/main/resources");


    }
}
