package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query (value = "SELECT pd FROM ProductDetail pd JOIN FETCH pd.product WHERE pd.product.id = :productId", nativeQuery = true)
    List<ProductDetail> findTop8ByProductId (@Param ("productId") long productId);

    List<ProductDetail> findProductDetailsByProductId(long productId);
}

