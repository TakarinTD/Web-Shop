package com.example.demo.service.impl;

import com.example.demo.entity.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public Optional<ProductDetail> findProductDetail(Long productId, Long productColorId, Long productSizeId) {
        Optional<ProductDetail> optional = productDetailRepository.findByProductIdAndProductColorIdAndProductSizeId(productId, productColorId, productSizeId);
        return Optional.ofNullable(optional.orElse(null));
    }
}
