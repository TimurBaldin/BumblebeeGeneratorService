package com.rufus.bumblebee.Main.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @RequestMapping("/about.html")
    public String about() {
        return "about";
    }
}
