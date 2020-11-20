package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface ProductColorService {
    List<ProductColor> findProductColors(Long productId);

}