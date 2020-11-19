package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String viewMen(Model model,
                          @RequestParam("name") String categoryName,
                          @RequestParam("page") int pageNum,
                          @RequestParam("sortField") String sortField,
                          @RequestParam("sortDir") String sortDir,
                          @RequestParam("pageSize") int pageSize) {

        long categoryId = categoryService.findCategoryByName(categoryName).getId();
        Page<Product> page = productService.pageProductByCategoryId(categoryId, pageNum, pageSize, sortField, sortDir);
        model.addAttribute("cateName", categoryName);
        model.addAttribute("listProduct", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("listColor", productService.getListColorAndAmount(categoryId));
        model.addAttribute("listSize", productService.getListSizeAndAmount(categoryId));
        return "category";
    }
}
