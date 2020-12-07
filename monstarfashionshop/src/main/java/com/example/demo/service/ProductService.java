package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductColor;
import com.example.demo.entity.ProductSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product getProductByIdProduct(Long id);

    ProductDTO convertProductToProductDTO(Product product);

    Page<Product> getProductsByCategoryIdAndPromotionId(Long categoryId, Long promotionId, int pageNum, int pageSize, String sortField, String sortDir);

    List<Product> getTopProductSale();

    List<ProductDTO> getProductsByCategoryId(long categoryId);

    Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize, String sortField, String sortDir);

    Page<Product> findProductByKeyword(String keyword, List<Long> colorIds, List<Long> sizeIds, float price1, float price2, int page, int pageSize, String sortField, String sortDir);

    Page<Product> findProductsWithCondition(Long categoryId, List<Long> colorIds, List<Long> sizeIds, float price1, float price2, int page, int pageSize, String sortField, String sortDir);

    Map<ProductColor, Integer> getListColorAndAmount(Long categoryId);

    Map<ProductColor, Integer> getListColorAndAmount(String keyword);

    Map<ProductSize, Integer> getListSizeAndAmount(Long categoryId);

    Map<ProductSize, Integer> getListSizeAndAmount(String keyword);

}
