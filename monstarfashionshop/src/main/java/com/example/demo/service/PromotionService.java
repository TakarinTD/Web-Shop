package com.example.demo.service;

import com.example.demo.entity.Promotion;

import java.util.Date;
import java.util.List;

public interface PromotionService {

    Promotion findById(Long id);

    List<Promotion> getAllPromotion(Date today);
}
