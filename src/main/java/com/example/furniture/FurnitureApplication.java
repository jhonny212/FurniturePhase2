package com.example.furniture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class FurnitureApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FurnitureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
