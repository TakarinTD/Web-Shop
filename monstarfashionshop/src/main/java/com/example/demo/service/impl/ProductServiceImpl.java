package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProductsByCategoryId(long categoryId) {
        List<ProductDTO> listProductDTO = new ArrayList<>();
        List<Product> listProducts = productRepository.findALlByCategoryId(categoryId);
        listProducts.forEach(product -> {
            listProductDTO.add(convertProductToProductDTO(product));
        });
        return listProductDTO;
    }

    @Override
    public ProductDTO convertProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPurchasePrice(product.getPurchasePrice());
        productDTO.setSalePrice(product.getSalePrice());

        return productDTO;
    }

    public Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    public List<Product> findProducts(String categoryName){
        return productRepository.findProductsByCategoryCategoryName(categoryName);
    }

    @Override
    public List<ProductDTO> getProductsTopSaleMan() {
        return null;
    }

    @Override
    public List<ProductDTO> getProductsTopSaleWomen() {
        return null;
    }
}
