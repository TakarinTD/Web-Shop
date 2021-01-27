package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    public CategoryServiceImpl categoryService;

    @Autowired
    public ProductService productService;

    public ModelAndView _mvShare;

    public BaseController() {
        this._mvShare = new ModelAndView();
    }

    @PostConstruct
    public ModelAndView Init() {
        List<Category> listCategory = categoryService.getListCategory();
        _mvShare.addObject("categories", listCategory);
        return _mvShare;
    }

    public void paginateCommon(int page, int pageSize, String sortField, String sortDir) {
        _mvShare.addObject("currentPage", page);
        _mvShare.addObject("pageSize", pageSize);
        _mvShare.addObject("sortField", sortField);
        _mvShare.addObject("sortDir", sortDir);
    }

}
