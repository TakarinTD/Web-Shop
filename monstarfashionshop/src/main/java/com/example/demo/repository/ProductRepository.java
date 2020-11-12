package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import org.springframework.data.repository.query.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryCategoryName (String categoryName);
    Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageable);
}
