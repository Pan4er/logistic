package com.example.logistic.controller;

import com.example.logistic.entity.Good;
import com.example.logistic.entity.Store;
import com.example.logistic.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestC {
    @Autowired
    StoreRepo storeRepo;
    @GetMapping("gbs/{id}")
    public String getGoodsByStore(@PathVariable int id)
    {
       Store thisStore =  storeRepo.findById(id).get();
       return thisStore.getGoods().get(1).getName();
    }

}
