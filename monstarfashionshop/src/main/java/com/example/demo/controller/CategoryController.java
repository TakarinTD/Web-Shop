package com.example.demo.controller;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController {

    @GetMapping("/category")
    public ModelAndView viewMen(@RequestParam("name") String categoryName,
                                @RequestParam("page") int pageNum,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam("pageSize") int pageSize) {

        long categoryId = categoryService.findCategoryByName(categoryName).getId();
        Page<Product> page = productService.pageProductByCategoryId(categoryId, pageNum, pageSize, sortField, sortDir);
        _mvShare.addObject("cateName", categoryName);
        _mvShare.addObject("listProduct", page.getContent());
        _mvShare.addObject("totalPages", page.getTotalPages());
        _mvShare.addObject("listColor", productService.getListColorAndAmount(categoryId));
        _mvShare.addObject("listSize", productService.getListSizeAndAmount(categoryId));
        paginateCommon(pageNum, pageSize, sortField, sortDir);
        _mvShare.setViewName("category");
        return _mvShare;
    }
}
