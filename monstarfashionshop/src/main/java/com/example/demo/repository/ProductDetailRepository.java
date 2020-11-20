package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query (value = "SELECT pd FROM ProductDetail pd JOIN FETCH pd.product WHERE pd.product.id = :productId", nativeQuery = true)
    List<ProductDetail> findTop8ByProductId (@Param ("productId") Long productId);

    List<ProductDetail> findProductDetailsByProductId (Long productId);

    @Query (value = " select quantity_available from product_detail where product_id = ?1 and product_color_id = ?2 and product_size_id = ?3 ", nativeQuery = true)
    Integer findQuantitiesByProductColorSize (Long productId, Long colorId, Long sizeId);

}

