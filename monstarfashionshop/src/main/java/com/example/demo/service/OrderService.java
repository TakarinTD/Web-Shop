package com.example.demo.service;

import com.example.demo.entity.Order;
import java.util.*;

public interface OrderService {

    List<Order> findAllOrder();
    List<Order> findByStatus(int status);
    boolean save(Order order);
}
