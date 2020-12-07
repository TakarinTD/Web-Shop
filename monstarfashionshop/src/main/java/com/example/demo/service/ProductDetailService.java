package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.*;

public interface ProductDetailService {

    Optional<ProductDetail> findById(Long idProductDetail);

    Optional<ProductDetail> findProductDetail(Long productId, Long productColorId, Long productSizeId);

    List<ProductDetail> findProductDetailBYProductId(Long productId);

    ProductDetail saveProductDetail(ProductDetail productDetail);

    ProductDetail findProductDetailPCS(Long productId, Long productColorId, Long productSizeId);
}
