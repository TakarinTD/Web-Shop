package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrder () {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus (int status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public boolean save(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
//            System.out.println("order is saved with id : " + savedOrder.getId());
            return true;
        }catch (RuntimeException e) {
            // add log errors or other logic here
            e.printStackTrace();
        }
        return false;
    }
}
