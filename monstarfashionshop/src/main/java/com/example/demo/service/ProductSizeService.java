package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface ProductSizeService {
    List<ProductSize> findProductSizes(Long productId);
}
