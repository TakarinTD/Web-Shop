package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductColor;
import com.example.demo.entity.ProductDetail;
import com.example.demo.entity.ProductSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface ProductService {

    ProductDTO convertProductToProductDTO(Product product);

    List<ProductDTO> getProductsByCategoryId(long categoryId);

    List<ProductDTO> getProductsTopSaleMan();

    List<ProductDTO> getProductsTopSaleWomen();

    Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize, String sortField, String sortDir);

    public List<Product> findProducts(String categoryName);

    Page<Product> findProductByKeyword(String keyword, int page, int pageSize, String sortField, String sortDir);

    Page<Product> findProductsWithCondition(Long categoryId, List<Long> colorIds, List<Long> sizeIds, float price1, float price2, int page, int pageSize, String sortField, String sortDir);

    Map<ProductColor, Integer> getListColorAndAmount(Long categoryId);

    Map<ProductSize, Integer> getListSizeAndAmount(Long categoryId);
}
