package com.goatz.task_manager_pro;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaskManagerProApplication {

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
