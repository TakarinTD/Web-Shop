package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.servlet.*;

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

    @RequestMapping (value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied () {
        ModelAndView model = new ModelAndView();
        model.setViewName("/access_denied");
        return model;
    }
}
