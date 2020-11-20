package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface ProductDetailService {
    List<ProductDetail> findProductDetails(Long productId);
    int findQuantity(Long productId, Long colorId, Long sizeId);
}
