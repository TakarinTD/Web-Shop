package com.example.demo.service.impl;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.ProductDetail;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public HashMap<Long, OrderDetail> addCart(Long idProductDetail, int quantity, HashMap<Long, OrderDetail> cart) {
        // create a new orderDetail
        OrderDetail orderDetail;
        // find productDetail with id
        ProductDetail productDetail = productDetailRepository.getOne(idProductDetail);
        if (productDetail != null && cart.containsKey(idProductDetail)) {  // product exits in cart
            orderDetail = cart.get(idProductDetail);
            orderDetail.setQuantitiesProduct(orderDetail.getQuantitiesProduct() + quantity);
            orderDetail.setTotalProductPay(orderDetail.getQuantitiesProduct() * productDetail.getProduct().getSalePrice());
        } else { // product is not exits in in cart
            orderDetail = new OrderDetail();
            orderDetail.setProductDetail(productDetail);
            orderDetail.setQuantitiesProduct(quantity);
            orderDetail.setTotalProductPay(productDetail.getProduct().getSalePrice() * quantity);
        }
        cart.put(idProductDetail, orderDetail);
        return cart;
    }

    @Override
    public HashMap<Long, OrderDetail> editCart(Long idProductDetail, int quantity, HashMap<Long, OrderDetail> cart) {
        if (cart.containsKey(idProductDetail)) {  // product exits in db
            OrderDetail orderDetail = cart.get(idProductDetail);
            orderDetail.setQuantitiesProduct(quantity);
            orderDetail.setTotalProductPay(quantity * orderDetail.getProductDetail().getProduct().getSalePrice());
            cart.put(idProductDetail, orderDetail);
        }
        return cart;
    }

    @Override
    public HashMap<Long, OrderDetail> removeItemCart(Long id, HashMap<Long, OrderDetail> cart) {
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    @Override
    public float totalPay(HashMap<Long, OrderDetail> cart) {
        float totalPrice = 0;
        for (Map.Entry<Long, OrderDetail> itemCart : cart.entrySet()) {
            totalPrice += itemCart.getValue().getTotalProductPay();
        }
        return totalPrice;
    }
}