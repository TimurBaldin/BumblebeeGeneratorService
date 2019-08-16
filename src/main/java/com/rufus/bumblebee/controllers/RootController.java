package com.rufus.bumblebee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping(value = {"/", "/index"})
    public String start() {
        return "index";
    }

    @RequestMapping("/about.html")
    public String about() {
        return "about";
    }

    @RequestMapping("/howuse.html")
    public String howuse() {
        return "howuse";
    }

    @RequestMapping("/generator.html")
    public String generator() {
        return "generator";
    }

    @RequestMapping("/testsuite.html")
    public String testsuite() {
        return "testsuite";
    }

}
