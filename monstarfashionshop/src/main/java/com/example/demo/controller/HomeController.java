package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public String viewHome(Model model) {
        List<Category> listCategory = categoryService.getListCategory();
        List<ProductDTO> listProductMen = productService.getProductsByCategoryId(16);
        List<ProductDTO> listProductWomen = productService.getProductsByCategoryId(1);
        model.addAttribute("categories", listCategory);
        model.addAttribute("productWomen", listProductWomen);
        model.addAttribute("productMen", listProductMen);
        return "home";
    }
}
