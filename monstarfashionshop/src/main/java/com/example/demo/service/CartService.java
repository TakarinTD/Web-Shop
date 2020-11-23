package com.example.demo.service;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;

import java.util.HashMap;

public interface CartService {

    HashMap<Long, OrderDetail> addCart(Long idProductDetail, int quantity, HashMap<Long, OrderDetail> cart);
    HashMap<Long, OrderDetail> editCart(Long idProductDetail, int quantity, HashMap<Long, OrderDetail> cart);
    HashMap<Long, OrderDetail> removeItemCart(Long id, HashMap<Long, OrderDetail> cart);
    float totalPay(HashMap<Long, OrderDetail> cart);
}
