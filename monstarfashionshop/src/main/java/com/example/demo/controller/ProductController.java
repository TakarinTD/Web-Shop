package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/{id}/detail")
    public ModelAndView viewProductDetail(@PathVariable("id") Long idProduct) {

        TreeSet<ProductColor> productColors = new TreeSet<>();
        TreeSet<ProductSize> productSizes = new TreeSet<>();
        TreeMap<Long, String> mapImage = new TreeMap<>();
        try {
            Product product = productService.getProductByIdProduct(idProduct);
            product.getProductDetails().forEach(productDetail -> {
                mapImage.put(productDetail.getProductColor().getId(), productDetail.getImage());
                productSizes.add(productDetail.getProductSize());
                productColors.add(productDetail.getProductColor());
            });
            _mvShare.addObject("product", product);
        }catch (RuntimeException e) {
            e.printStackTrace();
            _mvShare.setViewName("404");
            return _mvShare;
        }
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.addObject("images", mapImage);
        _mvShare.addObject("productColors", productColors);
        _mvShare.addObject("productSizes", productSizes);
        _mvShare.addObject("oneReview", new Review());
        _mvShare.setViewName("product_detail");
        return _mvShare;
    }

    @GetMapping("getAmountOfProduct")
    @ResponseBody
    public int getAmountOfProduct(@RequestParam("productId") Long productId, @RequestParam("producColorId") Long productColorId, @RequestParam("productSizeId") Long ProductSizeId) {
        Optional<ProductDetail> productDetail = productDetailService.findProductDetail(productId, productColorId, ProductSizeId);
        return productDetail.isPresent() ? productDetail.get().getWarehouse().getQuantityAvailable(): 0;
    }
}
