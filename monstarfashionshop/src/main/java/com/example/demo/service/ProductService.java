package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetail;

import java.util.List;

public interface ProductService {

    ProductDTO convertProductToProductDTO(Product product);
    List<ProductDTO> getProductsByCategoryId(long categoryId);
    List<ProductDTO> getProductsTopSaleMan();
    List<ProductDTO> getProductsTopSaleWomen();

}
