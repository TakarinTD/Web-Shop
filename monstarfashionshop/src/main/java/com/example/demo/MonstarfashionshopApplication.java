package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.example.demo")
public class MonstarfashionshopApplication {

    public static void main (String[] args) {
        SpringApplication.run(MonstarfashionshopApplication.class, args);
    }

}
