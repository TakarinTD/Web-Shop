package com.example.demo.controller.admin;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.parameters.*;
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
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductColorRepository productColorRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private WareHouseService wareHouseService;

    @GetMapping ("/product")
    public String product (Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product_admin";
    }

    @GetMapping ("/product_detail/{id}")
    public String productDetail (@PathVariable ("id") Long productId, Model model) {
        Product product = productService.getProductByIdProduct(productId);
        model.addAttribute("product", product);
        model.addAttribute("promotions", promotionService.findPromotions());
        model.addAttribute("promotionId", promotionService.findPromotionByProductId(productId).getId());
        model.addAttribute("pDetails", productDetailService.findProductDetailBYProductId(productId));
        model.addAttribute("colors", productColorRepository.findAll());
        model.addAttribute("sizes", productSizeRepository.findAll());
        System.out.println(productDetailService.findProductDetailBYProductId(productId));
        return "product_detail_admin";
    }

    @GetMapping ("/find_product/{id}")
    @ResponseBody
    public Product findProduct (@PathVariable (value = "id") Long productId, Model model) {
        System.out.println(productId);
        return productService.getProductByIdProduct(productId);
    }

    @PostMapping ("/edit_product")
    @ResponseBody
    public ResponseEntity edit (@RequestBody Product productEdit, @RequestParam (value = "promotion", required = false) Long promotionId) {
        Product product = productService.getProductByIdProduct(productEdit.getId());
        productEdit.setCategory(product.getCategory());
        productEdit.setPurchasePrice(product.getPurchasePrice());
        productEdit.setReviews(product.getReviews());
        productEdit.setPromotion(promotionService.findPromotion(promotionId));
        try {
            return ResponseEntity.ok(productService.saveProduct(productEdit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping ("/add_detail_product")
    @ResponseBody
    public ResponseEntity addDetailProduct (@RequestBody ProductDetail productDetail, @RequestParam (name = "quantityAvailable", required = false) int quantityAvailable, @RequestParam (name = "productId", required = false) Long productId, @RequestParam (name = "colorId", required = false) Long colorId, @RequestParam (name = "sizeId", required = false) Long sizeId) {
        productDetail.setProduct(productService.getProductByIdProduct(productId));
        productDetail.setProductColor(productColorRepository.findById(colorId).get());
        productDetail.setProductSize(productSizeRepository.findById(sizeId).get());
        Warehouse warehouse = new Warehouse();
        try {
            if (productDetailService.findProductDetail(productDetail.getProduct().getId(), productDetail.getProductColor().getId(), productDetail.getProductSize().getId()).isPresent()) {
                ProductDetail productDetailExits = productDetailService.findProductDetailPCS(productDetail.getProduct().getId(), productDetail.getProductColor().getId(), productDetail.getProductSize().getId());
                warehouse = wareHouseService.findByProductDetailId(productDetailExits.getId());
                warehouse.setQuantityAvailable(quantityAvailable + warehouse.getQuantityAvailable());
                return ResponseEntity.ok(wareHouseService.saveWarehouse(warehouse));
            }
            productDetailService.saveProductDetail(productDetail);
            warehouse.setQuantityAvailable(quantityAvailable);
            warehouse.setProductDetail(productDetail);
            return ResponseEntity.ok(wareHouseService.saveWarehouse(warehouse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping ("/find_detail/{id}")
    @ResponseBody
    public ProductDetail findProductDetail (@PathVariable (value = "id") Long productDetailId, Model model) {
        System.out.println(productDetailId);
        return productDetailService.findById(productDetailId).get();
    }

    @PostMapping ("/addQuantity")
    @ResponseBody
    public ResponseEntity addQuantity (@RequestBody ProductDetail productDetail, @RequestParam (name = "quantityAvailable", required = false) int quantityAvailable) {
        Warehouse warehouse = wareHouseService.findByProductDetailId(productDetail.getId());
        warehouse.setQuantityAvailable(quantityAvailable + warehouse.getQuantityAvailable());
        try {
            return ResponseEntity.ok(wareHouseService.saveWarehouse(warehouse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
