package com.example.demo.service.impl;

import com.example.demo.entity.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.ProductDetailService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public Optional<ProductDetail> findById(Long idProductDetail) {
        return Optional.ofNullable(productDetailRepository.findById(idProductDetail).orElse(null));
    }

    @Override
    public Optional<ProductDetail> findProductDetail(Long productId, Long productColorId, Long productSizeId) {
        Optional<ProductDetail> optional = productDetailRepository.findByProductIdAndProductColorIdAndProductSizeId(productId, productColorId, productSizeId);
        return Optional.ofNullable(optional.orElse(null));
    }

    @Override
    public List<ProductDetail> findProductDetailBYProductId (Long productId) {
        return productDetailRepository.findTop8ByProductId(productId);
    }

    @Override
    public ProductDetail saveProductDetail (ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail findProductDetailPCS (Long productId, Long productColorId, Long productSizeId) {
        return productDetailRepository.findByProductIdAndProductColorIdAndProductSizeId(productId, productColorId, productSizeId).get();
    }
}
