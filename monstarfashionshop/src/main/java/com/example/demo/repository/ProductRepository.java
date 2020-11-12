package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.id = :categoryId")
    List<Product> findALlByCategoryId(@Param("categoryId") long categoryId);
    List<Product> findProductsByCategoryCategoryName (String categoryName);
    Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageable);
}
