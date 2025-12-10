package com.goatz.task_manager_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Displays the home page.
     * Endpoint: GET /
     * @return Thymeleaf template name for the index page
     */
    @GetMapping("/")
    public String index(){
        return "index2"; // Placeholder, Change name when "index2" is created.
    }
}
