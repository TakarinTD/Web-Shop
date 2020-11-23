package com.example.demo.service;

import com.example.demo.entity.ProductDetail;

import java.util.Optional;

public interface ProductDetailService {

    Optional<ProductDetail> findProductDetail(Long productId, Long productColorId, Long productSizeId);
}
