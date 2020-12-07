package com.example.demo.repository;

import com.example.demo.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByProductsId(Long productId);

    List<Promotion> getAllByStartDateLessThanAndEndDateGreaterThan(Date afterStart, Date beforeEnd);
}
