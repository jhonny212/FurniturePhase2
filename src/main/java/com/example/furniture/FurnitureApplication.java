package com.example.furniture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FurnitureApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FurnitureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
