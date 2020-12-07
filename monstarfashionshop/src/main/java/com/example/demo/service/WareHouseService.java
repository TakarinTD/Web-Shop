package com.example.demo.service;

import com.example.demo.entity.Warehouse;

public interface WareHouseService {

    boolean updateQuantity(Warehouse warehouse);

    Warehouse findByProductDetailId(Long productDetailId);

    Warehouse saveWarehouse(Warehouse warehouse);
}
