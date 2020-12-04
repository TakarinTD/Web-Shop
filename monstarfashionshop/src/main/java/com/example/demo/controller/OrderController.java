package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.security.*;
import java.util.*;
import javax.jws.*;
import javax.management.modelmbean.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @GetMapping("/order_history")
    public String orderHistory(Model model, Principal principal){
//        User user = (User) session.getAttribute("user");
        User user=userService.findUserByEmail(principal.getName());
        System.out.println(user.getFullName());
        List<Order> orders=orderRepository.findAllOrderByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "order_history";
    }

    @GetMapping("/detail_order/{id}")
    public String detailOrder(Model model, @PathVariable("id") Long orderId){
        Order order=orderRepository.findById(orderId).get();
        User user=userRepository.findUserByOrdersId(orderId);
        List<OrderDetail> orderDetails= orderDetailRepository.findAllOrderDetailByOrderId(orderId);
        System.out.println(orderDetailRepository.findAllOrderDetailByOrderId(orderId));
        model.addAttribute("order",order);
        model.addAttribute("user",user);
        model.addAttribute("orderDetails",orderDetails);
        return "detail_order";
    }

    @PostMapping("/huy_don/{id}")
    public String huyDon(@PathVariable("id") Long orderId, Model model){
        Order order=orderRepository.findById(orderId).get();
        order.setStatus(3);
        orderRepository.save(order);
        User user=userRepository.findUserByOrdersId(orderId);
        List<OrderDetail> orderDetails= orderDetailRepository.findAllOrderDetailByOrderId(orderId);
        System.out.println(orderDetailRepository.findAllOrderDetailByOrderId(orderId));
        model.addAttribute("order",order);
        model.addAttribute("user",user);
        model.addAttribute("orderDetails",orderDetails);
        return "detail_order";
    }
}
