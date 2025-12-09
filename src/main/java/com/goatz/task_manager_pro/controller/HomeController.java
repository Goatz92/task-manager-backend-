package com.goatz.task_manager_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index2"; // Placeholder, Change name when "index2" is created.
    }
}
