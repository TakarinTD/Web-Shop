package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders ord WHERE ord.user_id = :userId ORDER BY order_date desc", nativeQuery = true)
    public List<Order> findAllOrderByIdUser(@Param("userId") Long id);


}
