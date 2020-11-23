package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductColorService productColorService;
    @Autowired
    private ProductSizeService productSizeService;

    int quantity;
    @GetMapping ("/product_detail_page/{id}")
    public String viewProductDetail (Model model, @PathVariable (value = "id") Long productId) {
        Optional<Product> product = productService.findProduct(productId);
        System.out.println(product.get().getId());
        model.addAttribute("product", product.get());
        model.addAttribute("details", productDetailService.findProductDetails(productId));
        model.addAttribute("colors", productColorService.findProductColors(productId));
        model.addAttribute("sizes", productSizeService.findProductSizes(productId));
        return "product_detail_page";
    }
}
