package com.goatz.task_manager_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    // All paths are temp.
    @GetMapping("/login")
    public String login(Principal principal) {
        return principal == null ? "loginb" : "redirect:/task-app/task";
    }

    @GetMapping("/")
    public String root(Principal principal) {
        return principal == null ? "index2" : "redirect:/task-app/task";
    }
}
