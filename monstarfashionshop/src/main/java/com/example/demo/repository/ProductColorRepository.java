package com.example.demo.repository;

import com.example.demo.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

    @Query (value = " SELECT product_color.* FROM product_color, product_detail WHERE  product_color.id = product_color_id and product_id = ?1 ", nativeQuery = true)
    List<ProductColor> findProductColors(Long productId);
}
