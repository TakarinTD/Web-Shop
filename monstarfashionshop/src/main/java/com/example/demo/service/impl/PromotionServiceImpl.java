package com.example.demo.service.impl;

import com.example.demo.entity.Promotion;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Promotion> getAllPromotion(Date today) {
        return promotionRepository.getAllByStartDateLessThanAndEndDateGreaterThan(today, today);
    }
}
