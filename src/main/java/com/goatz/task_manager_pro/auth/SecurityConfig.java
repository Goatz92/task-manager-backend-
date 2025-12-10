package com.goatz.task_manager_pro.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
/**
 * Spring Security configuration for the application.
 * Defines endpoint access rules, login/logout behavior, and disables CSRF.
 */
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     * Sets up endpoint permissions, login/logout, and disables CSRF.
     * @param http HttpSecurity configuration object
     * @return Configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index.html").permitAll()
                        .requestMatchers("/task/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/task/edit/{uuid}").hasAuthority("EDIT_TASK")
                        .requestMatchers(HttpMethod.GET, "/task/delete/{uuid}").hasAuthority("EDIT_TASK")
                        .requestMatchers(HttpMethod.POST, "/task/edit").hasAuthority("EDIT_TASK")
                        .requestMatchers("/users/tasks/**").hasAnyRole("ADMIN")
                        .requestMatchers("/users/admin/**").hasRole("ADMIN")
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .anyRequest().authenticated()
                )

                        .formLogin(formLogin -> formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                        )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
