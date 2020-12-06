package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public List<Promotion> findPromotions () {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findPromotion (Long promotionId) {
        return promotionRepository.findById(promotionId).get();
    }

    @Override
    public Promotion findPromotionByProductId (Long productId) {
        return promotionRepository.findByProductsId(productId);
    }
}
