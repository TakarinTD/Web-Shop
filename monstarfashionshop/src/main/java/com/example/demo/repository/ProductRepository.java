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

    @Query(value = "SELECT DISTINCT p.* FROM product AS p\n" +
            "INNER JOIN promotion AS pt ON p.promotion_id = pt.id\n" +
            "INNER JOIN product_detail AS pd ON p.id = pd.product_id\n" +
            "INNER JOIN order_detail AS od ON pd.id = od.product_detail_id\n" +
            "ORDER BY quantities_product DESC LIMIT 4", nativeQuery = true)
    List<Product> getTopProductSale();

    Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageable);

    List<Product> findProductByIdAndCategoryId(Long productId, Long CategoryId);

    Page<Product> findProductsByCategoryIdAndPromotionId(Long categoryId, Long idPromotion, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p WHERE p.salePrice BETWEEN :price1 AND :price2 AND " +
            "p.productName LIKE %:keyword% OR p.category.categoryName LIKE %:keyword%")
    Page<Product> findProductsByProductNameOrCategoryName0(@Param("keyword") String keyword, @Param("price1") float price1, @Param("price2") float price2, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productColor.id IN :colorIds AND pd.productSize.id IN :sizeIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2 AND " +
            "p.productName LIKE %:keyword% OR p.category.categoryName LIKE %:keyword%")
    Page<Product> findProductsByProductNameOrCategoryName1(@Param("keyword") String keyword, @Param("price1") float price1, @Param("price2") float price2, @Param("colorIds") List<Long> colorIds, @Param("sizeIds") List<Long> sizeIds, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productColor.id IN :colorIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2 AND " +
            "p.productName LIKE %:keyword% OR p.category.categoryName LIKE %:keyword%")
    Page<Product> findProductsByProductNameOrCategoryName2(String keyword, @Param("price1") float price1, @Param("price2") float price2, @Param("colorIds") List<Long> colorIds, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productSize.id IN :sizeIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2 AND " +
            "p.productName LIKE %:keyword% OR p.category.categoryName LIKE %:keyword%")
    Page<Product> findProductsByProductNameOrCategoryName3(String keyword, @Param("price1") float price1, @Param("price2") float price2, @Param("sizeIds") List<Long> sizeIds, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE p.category.id= :categoryId AND pd.productColor.id IN :colorIds AND pd.productSize.id IN :sizeIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional1( @Param("categoryId") Long categoryId, @Param("colorIds") List<Long> colorIds, @Param("sizeIds") List<Long> sizeIds, @Param("price1") float price1, @Param("price2") float price2, Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE p.category.id= :categoryId AND pd.productColor.id IN :colorIds AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional2(
            @Param("categoryId") Long categoryId,
            @Param("colorIds") List<Long> colorIds,
            @Param("price1") float price1,
            @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE p.category.id= :categoryId AND pd.productSize.id IN :sizeIds AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional3(
            @Param("categoryId") Long categoryId,
            @Param("sizeIds") List<Long> sizeIds,
            @Param("price1") float price1,
            @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE p.category.id= :categoryId AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional4(
            @Param("categoryId") Long categoryId,
            @Param("price1") float price1,
            @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productColor.id IN :colorIds AND pd.productSize.id IN :sizeIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional5(
            @Param("colorIds") List<Long> colorIds,
            @Param("sizeIds") List<Long> sizeIds,
            @Param("price1") float price1, @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productColor.id IN :colorIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional6(
            @Param("colorIds") List<Long> colorIds,
            @Param("price1") float price1, @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE pd.productSize.id IN :sizeIds " +
            "AND p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional7(
            @Param("sizeIds") List<Long> sizeIds,
            @Param("price1") float price1, @Param("price2") float price2,
            Pageable pageable
    );

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.productDetails pd " +
            "WHERE p.salePrice BETWEEN :price1 AND :price2")
    Page<Product> searchProductDetailsWithConditional8(
            @Param("price1") float price1, @Param("price2") float price2,
            Pageable pageable
    );
}
