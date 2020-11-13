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

    @GetMapping ("/category/{categoryId}/{pageNum}")
    public String viewMen (Model model, @PathVariable ("categoryId") Long categoryId, @PathVariable ("pageNum") int pageNum) {
        int pageSize = 5;
        Page<Product> page = productService.pageProductByCategoryId(categoryId, pageNum, pageSize);
        model.addAttribute("listProduct", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalElement", page.getTotalElements());
        return "men";
    }

    @GetMapping ("/category")
    public String viewMen (Model model) {
        model.addAttribute("mens", productService.findProducts("men"));
        System.out.println(productService.findProducts("men"));
        return "category";
    }

    @GetMapping ("/product_detail_page/{id}")
    public String viewProductDetail (Model model, @PathVariable (value = "id") Long productId) {
        Optional<Product> product = productService.findProduct(productId);
        System.out.println(product.get().getId());
        model.addAttribute("product", product.get());
        model.addAttribute("details", productDetailService.findProductDetails(productId));
        model.addAttribute("colors",productColorService.findProductColors(productId));
        model.addAttribute("sizes",productSizeService.findProductSizes(productId));
        return "product_detail_page";
    }
}
