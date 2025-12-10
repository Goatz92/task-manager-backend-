package com.goatz.task_manager_pro.auth;

import com.goatz.task_manager_pro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * Custom implementation of UserDetailsService for Spring Security.
 * Loads user details from the UserRepository for authentication.
 */
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Loads a user by username for authentication.
     * @param username Username string
     * @return UserDetails for the authenticated user
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username " + username));
    }
}
