package com.ecommerce.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// This annotation enables several key features in Spring Boot, such as autoconfiguration and component scanning.
public class EcomApplication {

    // Main method: entry point for the Spring Boot application.
    public static void main(String[] args) {
        // SpringApplication.run() method is used to launch the Spring Boot application.
        // It starts the embedded server (e.g., Tomcat) and initializes the Spring context.
        SpringApplication.run(EcomApplication.class, args);
    }

}
