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
    @GetMapping("stores")
    public String getStores(Model model)
    {
        storesService.renderStoresList(model);

        return "stores";
    }

    @PostMapping("editStoreName")
    public String editStoreName(@RequestParam("storeName") String storeName, @RequestParam("storeId") int storeId, Model model)
    {
        storesService.editStoreName(storeId,storeName);
        storesService.renderStoresList(model);
        return "stores";
    }

    @PostMapping("editStoreFullness")
    public String editStoreFullness(@RequestParam("storeFullness") double fullNess, @RequestParam("storeId") int storeId, Model model)
    {


        return storesService.editStoreFullness(storeId,fullNess,model);
    }

    @PostMapping("deleteStore")
    public String deleteStoreWithReplace(@RequestParam("storeToDelete") int storeToDeleteId,
                                         @RequestParam("storeToReplaceGoods") int storeToReplace,
                                         Model model)
    {
        return storesService.deleteStore(storeToDeleteId,storeToReplace,model);
    }

    @PostMapping("addNewStore")
    public String addNewStore(@RequestParam("storeName") String storeName,
                              @RequestParam("storeFullness") double storeSize,
                              Model model)
    {
        return storesService.addNewStore(storeName,storeSize,model);
    }




}
