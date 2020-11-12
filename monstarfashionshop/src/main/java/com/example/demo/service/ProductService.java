package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    public List<Product> findProducts(String categoryName){
        return productRepository.findProductsByCategoryCategoryName(categoryName);
    }

}
