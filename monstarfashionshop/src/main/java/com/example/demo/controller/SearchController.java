package com.example.demo.controller;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController extends BaseController{

    @GetMapping(value = "/search")
    public ModelAndView searchProductByCondition(
                                           @RequestParam(required = false) String keyword,
                                           @RequestParam int page,
                                           @RequestParam int pageSize,
                                           @RequestParam String sortField,
                                           @RequestParam String sortDir,
                                           @RequestParam(required = false) Long categoryId,
                                           @RequestParam(required = false) String colorIds,
                                           @RequestParam(required = false) String sizeIds,
                                           @RequestParam(required = false) Float price1,
                                           @RequestParam(required = false) Float price2) {
        Page<Product> pageResult;

        List<Long> listColorId = null;
        List<Long> listSizeId = null;

        if (price1 == null) {
            price1 = (float) 0;
        }
        if (price2 == null) {
            // find max price and set for price 2
            price2 = (float) 150000;
        }

        if (colorIds != null && !colorIds.equals("null")) {
            listColorId = (List<Long>) Arrays.asList(colorIds.split("\\."))
                    .stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        }

        if (sizeIds != null && !sizeIds.equals("null")) {
            listSizeId = (List<Long>) Arrays.asList(sizeIds.split("\\."))
                    .stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        }

        if (keyword != null) {
            pageResult = productService.findProductByKeyword(keyword, listColorId, listSizeId, price1, price2, page, pageSize, sortField, sortDir);
            _mvShare.addObject("listColor", productService.getListColorAndAmount(keyword));
            _mvShare.addObject("listSize", productService.getListSizeAndAmount(keyword));
        } else {
            pageResult = productService.findProductsWithCondition(categoryId, listColorId, listSizeId, price1, price2, page, pageSize, sortField, sortDir);
            _mvShare.addObject("listColor", productService.getListColorAndAmount(categoryId));
            _mvShare.addObject("listSize", productService.getListSizeAndAmount(categoryId));
        }

        _mvShare.addObject("keyword", keyword);
        _mvShare.addObject("colorIds", colorIds);
        _mvShare.addObject("sizeIds", sizeIds);
        _mvShare.addObject("price1", price1);
        _mvShare.addObject("price2", price2);
        _mvShare.addObject("listProductSearchResult", pageResult.getContent());
        _mvShare.addObject("totalPages", pageResult.getTotalPages());
        paginateCommon(page, pageSize, sortField, sortDir);
        _mvShare.setViewName("search");

        return _mvShare;
    }
}
