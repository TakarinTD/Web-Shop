package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public interface ProductService {

    ProductDTO convertProductToProductDTO(Product product);
    List<ProductDTO> getProductsByCategoryId(long categoryId);
    List<ProductDTO> getProductsTopSaleMan();
    List<ProductDTO> getProductsTopSaleWomen();
    Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize);
    public List<Product> findProducts(String categoryName);
}
