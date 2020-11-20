package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @GetMapping("/product_detail_page")
    public String viewProductDetail() {
        return "product_detail_page";
    }
}
