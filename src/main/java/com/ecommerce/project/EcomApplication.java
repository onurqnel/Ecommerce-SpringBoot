package com.ecommerce.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the eCommerce Spring Boot application.
 * <p>
 * The @SpringBootApplication annotation enables several key features in Spring Boot:
 * <p>
 * - Auto-configuration: Automatically configures Spring components based on project dependencies.
 * - Component scanning: Scans the package and its sub-packages for Spring components (e.g., @Controller, @Service).
 * - Spring Boot Configuration: Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 */
@SpringBootApplication
public class EcomApplication {

    /**
     * The main method, which serves as the entry point for the Spring Boot application.
     * <p>
     * This method uses SpringApplication.run() to launch the application. It starts the
     * embedded server (e.g., Tomcat) and initializes the Spring application context.
     *
     * @param args Command-line arguments passed to the application (if any).
     */
    public static void main(String[] args) {
        SpringApplication.run(EcomApplication.class, args);
    }

}
