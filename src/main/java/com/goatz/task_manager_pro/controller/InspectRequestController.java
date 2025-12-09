package com.goatz.task_manager_pro.controller;

import com.goatz.task_manager_pro.core.enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

@WebServlet
public class InspectRequestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username"); // Placeholder: Change username query param.
        String password = request.getParameter("password"); // Placeholder: Change password query param.
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Inspect session id
        response.getWriter().write("Session Id: " + request.getSession().getId());
        System.out.println("Session Id: " + request.getSession().getId());

        // Inspect request headers
        response.getWriter().write("\n\nHeaders\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue + "\n");
        }

        // Inspect request cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("Cookie Name: " + cookie.getName() + ", Cookie Value: " + cookie.getValue());
                    response.getWriter().write("Cookie Name: " + cookie.getName() + "Cookie Value: " + cookie.getValue() + "\n");
                }
            }
        }

        // Inspect session ID (JSSESIONID)
        HttpSession session = request.getSession(true);

        response.getWriter().write("\n\nNew Session ID \n"); // After login to tackle Session
        HttpSession oldSession = request.getSession(false); // Get current session if exists
        if (oldSession != null) {
            oldSession.invalidate(); // Kill old sess
        }
        HttpSession newSession = request.getSession(true); // Get current session if exists
        String sessionId = newSession.getId();

        request.getSession().setAttribute("username", username);
        String sessionUsername = (String) request.getSession().getAttribute("username");
        System.out.println("username: " + sessionUsername);
        response.getWriter().write("username: " + sessionUsername + "\n");

        if (username != null && username.equals("*****") && password.equals("*****")) { // Placeholder: TODO find a way to impl dotenv
            newSession.setAttribute("role", Role.ADMIN.name());
        } else {
            newSession.setAttribute("role", Role.USER.name());
        }

        System.out.println("New Session ID: " + sessionId);
        System.out.println("User in session role: " + newSession.getAttribute("role"));
        response.getWriter().write("New Session ID: " + sessionId + "\n");

        // Inspect session attributes

        // Inspect request URI and context path
        response.getWriter().write("\n\nRequest URI and Context Path");
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Context Path: " + request.getContextPath());
        System.out.println("Servlet Path: " + request.getServletPath());
        response.getWriter().write("Request URI: " + request.getRequestURI() + "\n");
        response.getWriter().write("Context Path: " + request.getContextPath() + "\n");
        response.getWriter().write("Servlet Path: " + request.getServletPath());

        response.setContentType("text/plain");
        response.getWriter().write("Request inspection completed. Check server logs for details");

        request.getRequestDispatcher("/task-app/tasks").forward(request, response);
        response.sendRedirect("/task-app/tasks");
    }

    @GetMapping("/debug-auth")
    public String debugAuth(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        model.addAttribute("username", auth.getName());
        model.addAttribute("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return "debug-auth"; // Template name (Thymeleaf)
    }
}
