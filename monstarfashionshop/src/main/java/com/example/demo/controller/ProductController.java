package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.ProductDetailService;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/{id}/detail")
    public String viewProductDetail(Model model, @PathVariable("id") Long idProduct) {
        List<Category> listCategory = categoryService.getListCategory();
        Product product = productService.getProductByIdProduct(idProduct);

        TreeSet<ProductColor> productColors = new TreeSet<>();
        TreeSet<ProductSize> productSizes = new TreeSet<>();
        TreeMap<Long, String> mapImage = new TreeMap<>();
        product.getProductDetails().forEach(productDetail -> {
            mapImage.put(productDetail.getProductColor().getId(), productDetail.getImage());
            productSizes.add(productDetail.getProductSize());
            productColors.add(productDetail.getProductColor());
        });
        model.addAttribute("images", mapImage);
        model.addAttribute("categories", listCategory);
        model.addAttribute("product", product);
        model.addAttribute("productColors", productColors);
        model.addAttribute("productSizes", productSizes);

        return "product_detail";
    }

    @GetMapping("getAmountOfProduct")
    @ResponseBody
    public int getAmountOfProduct(@RequestParam("productId") Long productId, @RequestParam("producColorId") Long productColorId, @RequestParam("productSizeId") Long ProductSizeId) {
        Optional<ProductDetail> productDetail = productDetailService.findProductDetail(productId, productColorId, ProductSizeId);
        return productDetail.isPresent() ? productDetail.get().getWarehouse().getQuantityAvailable(): 0;
    }
}
