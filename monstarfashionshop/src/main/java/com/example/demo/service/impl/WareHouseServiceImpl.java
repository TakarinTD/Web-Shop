package com.example.demo.service.impl;

import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WareHouseRepository;
import com.example.demo.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Override
    public boolean updateQuantity(Warehouse warehouse) {
        try {
            Warehouse savedWarehouse = wareHouseRepository.save(warehouse);
//            System.out.println("số lượng sản phẩm còn trong kho là : " + savedWarehouse.getQuantityAvailable());
            return true;
        }catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Warehouse findByProductDetailId (Long productDetailId) {
        return wareHouseRepository.findByProductDetailId(productDetailId);
    }

    @Override
    public Warehouse saveWarehouse (Warehouse warehouse) {
        return wareHouseRepository.save(warehouse);
    }
}
