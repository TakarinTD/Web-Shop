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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search")
    public String searchProductByDondition(Model model,
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
        } else {
            pageResult = productService.findProductsWithCondition(categoryId, listColorId, listSizeId, price1, price2, page, pageSize, sortField, sortDir);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("colorIds", colorIds);
        model.addAttribute("sizeIds", sizeIds);
        model.addAttribute("price1", price1);
        model.addAttribute("price2", price2);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("listColor", productService.getListColorAndAmount(categoryId));
        model.addAttribute("listSize", productService.getListSizeAndAmount(categoryId));

        model.addAttribute("listProductSearchResult", pageResult.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageResult.getTotalPages());
        return "search";
    }

}
