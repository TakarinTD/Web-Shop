package com.example.demo.controller;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCard(Model model, HttpSession session) {
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Long, OrderDetail>();
        }
        model.addAttribute("totalPay", cartService.totalPay(cart));
        model.addAttribute("categories", categoryService.getListCategory());
        return "cart";
    }

    @GetMapping(value = "/addCart")
    @ResponseBody
    public Integer addCart(HttpSession session,
                           @RequestParam("productId") long idProduct,
                           @RequestParam("productColorId") long idColor,
                           @RequestParam("productSizeId") long idSize,
                           @RequestParam("quantity") int quantity) {

        Optional<ProductDetail> productDetail = productDetailRepository.findByProductIdAndProductColorIdAndProductSizeId(idProduct, idColor, idSize);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent()) {
            if (cart == null) {
                cart = new HashMap<Long, OrderDetail>();
            }
            cart = cartService.addCart(productDetail.get().getId(), quantity, cart);
            session.setAttribute("cart", cart);
        }
        return cart.size();
    }

    @GetMapping(value = "/editItemCart")
    @ResponseBody
    public boolean editCart(HttpSession session,
                            @RequestParam("productDetailId") long idProductDetail,
                            @RequestParam("quantity") int quantity) {

        Optional<ProductDetail> productDetail = productDetailRepository.findById(idProductDetail);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent()) {
            cart = cartService.editCart(productDetail.get().getId(), quantity, cart);
            session.setAttribute("cart", cart);
            return true;
        }
        return false;
    }

    @GetMapping(value = "/removeItemCart")
    @ResponseBody
    public Integer deleteItemCart(HttpSession session, @RequestParam("productDetailId") long idProductDetail) {
        Optional<ProductDetail> productDetail = productDetailRepository.findById(idProductDetail);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent()) {
            cart = cartService.removeItemCart(idProductDetail, cart);
            session.setAttribute("cart", cart);
        }
        return cart.size();
    }

    @GetMapping("/checkout")
    public String checkOut() {
        return "checkout";
    }
}
