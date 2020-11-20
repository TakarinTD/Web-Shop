package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.*;
import javax.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> findProductDetails (Long productID) {
        return productDetailRepository.findProductDetailsByProductId(productID);
    }

    @Override
    public int findQuantity (Long productId, Long colorId, Long sizeId) {
        if(productDetailRepository.findQuantitiesByProductColorSize(productId, colorId, sizeId)==null){
            return 0;
        }
         else
         return productDetailRepository.findQuantitiesByProductColorSize(productId, colorId, sizeId);
    }

}
