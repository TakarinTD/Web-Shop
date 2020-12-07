package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.*;

public interface PromotionService {

    List<Promotion> findPromotions();

    Promotion findPromotion(Long promotionId);

    Promotion findPromotionByProductId(Long productId);

    Promotion findById(Long id);

    List<Promotion> getAllPromotion(Date today);
}
