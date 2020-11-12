package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.*;

@SpringBootApplication(scanBasePackages = "com.example.demo")
public class MonstarfashionshopApplication{

	public static void main(String[] args) {
		SpringApplication.run(MonstarfashionshopApplication.class, args);
	}
}
