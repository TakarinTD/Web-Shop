package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@EnableJpaAuditing
public class MonstarfashionshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonstarfashionshopApplication.class, args);
	}

}
