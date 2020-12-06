package com.example.demo.controller.admin;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/admin")
public class AdminProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService promotionService;

    @GetMapping ("/product")
    public String product (Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product_admin";
    }

    @GetMapping ("/product_detail/{id}")
    public String productDetail (@PathVariable ("id") Long productId, Model model) {
        Product product=productService.getProductByIdProduct(productId);
        model.addAttribute("product",product);
        model.addAttribute("promotions",promotionService.findPromotions());
        model.addAttribute("promotionId", promotionService.findPromotionByProductId(productId).getId());
        return "product_detail_admin";
    }

    @GetMapping ("/find_product/{id}")
    @ResponseBody
    public Product findProduct (@PathVariable (value = "id") Long productId,Model model) {
        System.out.println(productId);
        return productService.getProductByIdProduct(productId);
    }

    @PostMapping ("/edit_product")
    @ResponseBody
    public ResponseEntity edit (@RequestBody Product productDetail, @RequestParam(value = "promotion", required = false) Long promotionId) {
        Product product=productService.getProductByIdProduct(productDetail.getId());
        productDetail.setCategory(product.getCategory());
        productDetail.setPurchasePrice(product.getPurchasePrice());
        productDetail.setReviews(product.getReviews());
        productDetail.setPromotion(promotionService.findPromotion(promotionId));
        try {
            return ResponseEntity.ok(productService.saveProduct(productDetail));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
