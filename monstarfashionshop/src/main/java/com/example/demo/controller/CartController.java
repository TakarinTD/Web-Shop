package com.example.demo.controller;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetail;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Controller
@RequestMapping(value = "/cart")
public class CartController extends BaseController {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public ModelAndView viewCard(Model model, HttpSession session) {
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Long, OrderDetail>();
        }
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.setViewName("cart");
        return _mvShare;
    }

    @GetMapping(value = "/addCart")
    @ResponseBody
    public Map<String, Object> addCart(HttpSession session,
                                       @RequestParam("productId") long idProduct,
                                       @RequestParam("productColorId") long idColor,
                                       @RequestParam("productSizeId") long idSize,
                                       @RequestParam("quantity") int quantity) {

        Map<String, Object> objectMap = new HashMap<>();
        Optional<ProductDetail> productDetail = productDetailService.findProductDetail(idProduct, idColor, idSize);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent()) {
            int availableQuantities = productDetail.get().getWarehouse().getQuantityAvailable();
            if (cart == null) {
                cart = new HashMap<Long, OrderDetail>();
                if (quantity > availableQuantities) {
                    objectMap.put("cartSize", 0);
                    return objectMap;
                }
            }
            if (cart.containsKey(productDetail.get().getId())) {
                int newQuantities = cart.get(productDetail.get().getId()).getQuantitiesProduct() + quantity;
                if (newQuantities > availableQuantities) {
                    objectMap.put("cartSize", 0);
                    return objectMap;
                }
            }
            cart = cartService.addCart(productDetail.get().getId(), quantity, cart);
            session.setAttribute("totalPay", cartService.totalPay(cart));
            session.setAttribute("cart", cart);
        }
        objectMap.put("cartSize", cart.size());
        objectMap.put("itemsCart", cart.values());
        objectMap.put("totalPay", cartService.totalPay(cart));
        return objectMap;
    }

    @GetMapping(value = "/editItemCart")
    @ResponseBody
    public boolean editCart(HttpSession session,
                            @RequestParam("productDetailId") long idProductDetail,
                            @RequestParam("quantity") int quantity) {

        Optional<ProductDetail> productDetail = productDetailService.findById(idProductDetail);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent() && cart.containsKey(productDetail.get().getId())) {
            int availableQuantities = productDetail.get().getWarehouse().getQuantityAvailable();
            if (quantity > availableQuantities) {
                return false;
            }
            cart = cartService.editCart(productDetail.get().getId(), quantity, cart);
            session.setAttribute("cart", cart);
        }
        return true;
    }

    @GetMapping(value = "/removeItemCart")
    @ResponseBody
    public Integer deleteItemCart(HttpSession session, @RequestParam("productDetailId") long idProductDetail) {
        Optional<ProductDetail> productDetail = productDetailService.findById(idProductDetail);
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (productDetail.isPresent()) {
            cart = cartService.removeItemCart(idProductDetail, cart);
            session.setAttribute("cart", cart);
        }
        return cart.size();
    }

}
