package com.example.logistic.controller;

import com.example.logistic.entity.Store;
import com.example.logistic.repo.GoodRepo;
import com.example.logistic.repo.StoreRepo;
import com.example.logistic.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StoreController {
    @Autowired
    private StoresService storesService;
    @PostMapping(value = "goodsWithStore")
    public String getGoodsByStore(@RequestParam("SelectedValue") int storeId, Model model){
        return storesService.getGoodsByStore(storeId, model);
    }

}
