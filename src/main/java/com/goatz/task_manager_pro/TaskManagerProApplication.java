package com.goatz.task_manager_pro;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerProApplication {

	public static void main(String[] args) {
        // Load .env before Spring Boot starts
        Dotenv dotenv = Dotenv.load();

        System.out.println("DB_URL=" + dotenv.get("DB_URL"));
        System.out.println("DB_USER=" + dotenv.get("DB_USER"));
        System.out.println("DB_PASSWORD=" + dotenv.get("DB_PASSWORD"));

        // Set environment variables manually for Spring Boot
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USER"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("jwt.secret", dotenv.get("JWT_SECRET"));

		SpringApplication.run(TaskManagerProApplication.class, args);
	}

}
