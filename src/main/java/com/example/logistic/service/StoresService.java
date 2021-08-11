package com.example.logistic.service;

import com.example.logistic.entity.Good;
import com.example.logistic.entity.Store;
import com.example.logistic.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StoresService {
    @Autowired
    StoreRepo storeRepo;

    public String getGoodsByStore(int storeId,Model model)
    {
        Store currentStore = storeRepo.findById(storeId).get();

        model.addAttribute("storeName", String.format("Склад - %s",currentStore.getStore_name()));
        List<Good> goods = currentStore.getGoods();
        model.addAttribute("goodsList", goods);
        return "goodsWithStore";
    }

    public void renderStoresList(Model model)
    {
        List<Store> stores = ArraysOperations.toList(storeRepo.findAll());
        model.addAttribute("storesList", stores);
    }




}
