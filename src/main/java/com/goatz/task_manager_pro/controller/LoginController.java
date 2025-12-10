package com.goatz.task_manager_pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {

    // All paths are temp.
    /**
     * Displays the login page or redirects authenticated users to the main task page.
     * Endpoint: GET /login
     * @param principal The authenticated user principal (if any)
     * @return Login template name if not authenticated, otherwise redirect to tasks
     */
    @GetMapping("/login")
    public String login(Principal principal) {
        return principal == null ? "loginb" : "redirect:/task-app/task";
    }

    /**
     * Displays the root (home) page or redirects authenticated users to the main task page.
     * Endpoint: GET /
     * @param principal The authenticated user principal (if any)
     * @return Index template name if not authenticated, otherwise redirect to tasks
     */
    @GetMapping("/")
    public String root(Principal principal) {
        return principal == null ? "index2" : "redirect:/task-app/task";
    }
}
