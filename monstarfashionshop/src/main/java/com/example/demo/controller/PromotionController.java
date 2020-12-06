package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class PromotionController extends BaseController{

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotion")
    public ModelAndView viewPromotionPage() {
        Date today = new Date();
        List<Promotion> promotionList = promotionService.getAllPromotion(today);
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.addObject("promotions", promotionList);
        _mvShare.setViewName("promotion");
        return _mvShare;
    }

    @GetMapping("/promotion/products/{promotionId}")
    public ModelAndView getProductsOfPromotion(@PathVariable("promotionId") Long promotionId,
                                               @RequestParam("categoryId") Long categoryId,
                                               @RequestParam("page") int pageNum,
                                               @RequestParam("sortField") String sortField,
                                               @RequestParam("sortDir") String sortDir,
                                               @RequestParam("pageSize") int pageSize) {
        Promotion promotion = promotionService.findById(promotionId);

        if(promotion != null) {
            Page<Product> page = productService.getProductsByCategoryIdAndPromotionId(categoryId, promotionId, pageNum, pageSize, sortField, sortDir);
            paginateCommon(pageNum, pageSize, sortField, sortDir);
            _mvShare.addObject("productsPromotion", page.getContent());
            _mvShare.addObject("totalPages", page.getTotalPages());
        }
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.addObject("promotionId", promotionId);
        _mvShare.addObject("categoryId", categoryId);
        _mvShare.setViewName("promotion_detail");
        return _mvShare;
    }
}
