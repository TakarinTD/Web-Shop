package com.example.demo.controller;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    public  String user(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "user_admin";
    }
    @GetMapping("/product")
    public  String product(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "product_admin";
    }
}
