package com.example.demo.repository;

import com.example.demo.entity.OrderDetail;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllOrderDetailByOrderId(Long orderId);
}
