package com.example.demo.repository;

import com.example.demo.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query("SELECT pd FROM ProductDetail pd JOIN FETCH pd.product WHERE pd.product.id = :productId")
    List<ProductDetail> findTop8ByProductId(@Param("productId") long productId);

    @Query(value = "select sum(pd.quantity_available)\n" +
            "from product_detail as pd inner join product as p on pd.product_id = p.id\n" +
            "inner join product_color as pc on pd.product_color_id = pc.id\n" +
            "where p.category_id = :categoryId and pc.id = :colorId\n" +
            "order by pc.color_name asc ",
            nativeQuery = true)
    Integer countAvailableByCategoryAndColor(@Param("categoryId") Long categoryId, @Param("colorId") Long colorId);

    @Query(value = "select sum(pd.quantity_available)\n" +
            "from product_detail as pd inner join product as p on pd.product_id = p.id\n" +
            "inner join product_size as ps on pd.product_color_id = ps.id\n" +
            "where p.category_id = :categoryId and ps.id = :sizeId\n" +
            "order by ps.size_name asc ",
            nativeQuery = true)
    Integer countAvailableByCategoryAndSize(@Param("categoryId") Long categoryId, @Param("sizeId") Long sizeId);
}
