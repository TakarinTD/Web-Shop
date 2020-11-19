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
                                           @RequestParam(value = "keyword", required = false) String keyword,
                                           @RequestParam("page") int page,
                                           @RequestParam("pageSize") int pageSize,
                                           @RequestParam("sortField") String sortField,
                                           @RequestParam("sortDir") String sortDir,
                                           @RequestParam(value = "categoryId", required = false) String categoryId,
                                           @RequestParam(value = "colorIds", required = false) String colorIds,
                                           @RequestParam(value = "sizeIds", required = false) String sizeIds,
                                           @RequestParam(value = "price1", required = false) String price1,
                                           @RequestParam(value = "price2", required = false) String price2) {
        Page<Product> pageResult;

        Long cateId = null;
        List<Long> listColorId = null;
        List<Long> listSizeId = null;

        if (keyword != null) {
            pageResult = productService.findProductByKeyword(keyword, page, pageSize, sortField, sortDir);

        } else {

            if (!categoryId.equals("null")) {
                cateId = Long.parseLong(categoryId);
            }

            if (!colorIds.equals("null")) {
                listColorId = (List<Long>) Arrays.asList(colorIds.split("\\."))
                        .stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            }

            if (!sizeIds.equals("null")) {
                listSizeId = (List<Long>) Arrays.asList(sizeIds.split("\\."))
                        .stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            }

            pageResult = productService.findProductsWithCondition(cateId, listColorId, listSizeId, Float.parseFloat(price1), Float.parseFloat(price2), page, pageSize, sortField, sortDir);

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
        model.addAttribute("listColor", productService.getListColorAndAmount(cateId));
        model.addAttribute("listSize", productService.getListSizeAndAmount(cateId));

        model.addAttribute("listProductSearchResult", pageResult.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageResult.getTotalPages());
        return "search";
    }

}
