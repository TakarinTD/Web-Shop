package com.example.demo.controller.admin;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String orders(Model model){
        model.addAttribute("orders", orderService.findAllOrder());
        return "order_admin";
    }

    @GetMapping("/order")
    public String order(Model model, @RequestParam(name = "status", required = false) int status){
        List<Order> orders = orderService.findByStatus(status);
        model.addAttribute("orders", orders);
        return "order_admin";
    }
}
