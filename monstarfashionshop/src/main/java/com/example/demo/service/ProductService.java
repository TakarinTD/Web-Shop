package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import java.util.*;
import org.springframework.data.domain.*;

public interface ProductService {

    ProductDTO convertProductToProductDTO (Product product);

    List<ProductDTO> getProductsByCategoryId (long categoryId);

    List<Product> findProducts (String categoryName);

    Optional<Product> findProduct (Long id);

    Page<Product> pageProductByCategoryId (Long categoryId, int pageNum, int pageSize);

    List<ProductDTO> getProductsTopSaleMan ();

    List<ProductDTO> getProductsTopSaleWomen ();

}
