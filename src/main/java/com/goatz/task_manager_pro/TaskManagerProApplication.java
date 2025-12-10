package com.goatz.task_manager_pro;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
/**
 * Main entry point for the Task Manager Pro Spring Boot application.
 * <p>
 * Loads environment variables from .env before starting Spring Boot,
 * sets required system properties for database and JWT configuration,
 * and enables JPA auditing for entity timestamp fields.
 */
public class TaskManagerProApplication {

    /**
     * Starts the Spring Boot application.
     * Loads .env variables and sets system properties for DB and JWT before bootstrapping Spring.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Load .env before Spring Boot starts
        Dotenv dotenv = Dotenv.load();

        // Set environment variables manually for Spring Boot
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USER"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("jwt.secret", dotenv.get("JWT_SECRET"));

        SpringApplication.run(TaskManagerProApplication.class, args);
    }

}
