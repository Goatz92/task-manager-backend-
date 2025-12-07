package com.goatz.task_manager_pro.service;

import com.goatz.task_manager_pro.dto.AuthRequest;
import com.goatz.task_manager_pro.dto.AuthResponse;
import com.goatz.task_manager_pro.model.User;
import com.goatz.task_manager_pro.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final List<User> users = new ArrayList<>();

    public AuthService() {
        users.add(new User(1, "admin", "admin123"));
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(request.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
