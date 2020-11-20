package com.example.demo.repository;

import com.example.demo.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    @Query (value = " SELECT DISTINCT product_size.* FROM product_size, product_detail WHERE  product_size.id = product_size_id and product_id = ?1 ", nativeQuery = true)
    List<ProductSize> findProductSizes(Long productId);
}
