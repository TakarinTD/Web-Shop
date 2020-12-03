package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.web.servlet.*;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @GetMapping
    public ModelAndView viewHome() {
        List<ProductDTO> listProductMen = productService.getProductsByCategoryId(16);
        List<ProductDTO> listProductWomen = productService.getProductsByCategoryId(1);
        _mvShare.addObject("productWomen", listProductWomen);
        _mvShare.addObject("productMen", listProductMen);
        _mvShare.setViewName("home");
        return _mvShare;
    }

    @RequestMapping (value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied () {
        ModelAndView model = new ModelAndView();
        model.setViewName("/access_denied");
        return model;
    }
}
