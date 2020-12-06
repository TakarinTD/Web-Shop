package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.entity.*;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class CheckoutController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private WareHouseService wareHouseService;

    @GetMapping("/checkout")
    public ModelAndView viewCheckOut(HttpSession session, Model model) {
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (cart == null) {
            _mvShare.addObject("cartEmpty", "Không có sản phẩm nào trong giỏ hàng");
            _mvShare.setViewName("redirect:/cart");
            return _mvShare;
        }
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.addObject("flashShipPrice", 2000);
        _mvShare.addObject("slowShipPrice", 1000);
        _mvShare.addObject("categories", categoryService.getListCategory());
        _mvShare.addObject("order", new Order());
        _mvShare.setViewName("checkout");
        return _mvShare;
    }

    @PostMapping("/payment")
    public ModelAndView paymentOrder(@ModelAttribute("order") Order order, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        HashMap<Long, OrderDetail> cart = (HashMap<Long, OrderDetail>) session.getAttribute("cart");
        if (cart != null) {
            if (user != null) {
                order.setUser(user);
            }
            cart.values().forEach(orderDetail -> {
                //set relationship between order and orderDetail
                orderDetail.setOrder(order);
                order.addOrderDetail(orderDetail);
            });

            float totalPay = cartService.totalPay(cart);
            int vat = (int) (totalPay * 0.05);
            if(order.getDeliveryMethod().equals("flash")) {
                order.setTotalPay(totalPay + Constant.FLASH_SHIP_PRICE + vat);
            } else if(order.getDeliveryMethod().equals("slow")) {
                order.setTotalPay(totalPay + Constant.SLOW_SHIP_PRICE + vat);
            }

            try {
                // save order
                orderService.save(order);
                // sub quantity in warehouse after payment
                order.getOrderDetails().forEach(orderDetail -> {
                    Warehouse warehouse = orderDetail.getProductDetail().getWarehouse();
                    warehouse.setQuantityAvailable(orderDetail.getProductDetail().getWarehouse().getQuantityAvailable() - orderDetail.getQuantitiesProduct());
                    wareHouseService.updateQuantity(warehouse);
                });
                session.removeAttribute("cart");
            } catch (RuntimeException e){
                _mvShare.setViewName("404");
                return _mvShare;
            }
        }
        _mvShare.setViewName("order_success");
        return _mvShare;
    }
}
