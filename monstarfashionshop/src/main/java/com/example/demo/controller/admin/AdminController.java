package com.example.demo.controller.admin;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
