package com.goatz.task_manager_pro.controller;

import com.goatz.task_manager_pro.dto.AuthRequest;
import com.goatz.task_manager_pro.dto.AuthResponse;
import com.goatz.task_manager_pro.model.User;
import com.goatz.task_manager_pro.service.UserService;
import com.goatz.task_manager_pro.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.getUserByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());
            return new AuthResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
     }

     @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
     }
}