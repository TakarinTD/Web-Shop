package com.example.demo.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String viewCart(){
        return "cart";
    }

}
