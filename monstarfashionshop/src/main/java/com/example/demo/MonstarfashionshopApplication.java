package com.example.demo;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.example.demo")
public class MonstarfashionshopApplication
//        implements CommandLineRunner
{

    public static void main (String[] args) {
        SpringApplication.run(MonstarfashionshopApplication.class, args);
    }
    @Autowired
    private ProductDetailService productDetailService;

//    @Override
//    public void run (String... args) throws Exception {
//        System.out.println(productDetailService.findQuantity((long)15, (long)1, (long)1));
//    }
}
