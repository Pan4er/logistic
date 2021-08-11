package com.example.logistic.controller;


import com.example.logistic.entity.Good;
import com.example.logistic.service.DbInit;
import com.example.logistic.service.GoodService;
import com.example.logistic.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class GoodsController {

    @Autowired
    private GoodService goodService;
    @Autowired
    private StoresService storesService;
    @Autowired
    private DbInit dbInit;

    @GetMapping(value = "addGood")
    public String addGood(Model model) {
        storesService.renderStoresList(model);
        return "addGood";
    }

    @PostMapping(value = "addGood")
    public String addGood(
            @RequestParam("SelectedValue") String storeId,
            @RequestParam("goodName") String goodName,
            @RequestParam("goodPrice") String goodPrice,
            @RequestParam("goodSize") double goodSize,
            Model model
    ){
        return goodService.addNewGood(storeId,goodName,goodSize,goodPrice, model);
    }

    @GetMapping("goods")
    public String getAllGoods(Model model)
    {
        return goodService.getAll(model);
    }


    @GetMapping("searchByName")
    public String viewHomePage(Model model,
                               @Param("keyword") String keyword

    )
    {

            return goodService.renderGoodsSearch(model, keyword);

    }


    @RequestMapping(value = "SearchByName/editName")
    public String editGoodByName(@RequestParam("goodId") String id, @RequestParam("goodName") String goodName)
    {
        goodService.editGoodName(goodName, id);
        return "searchByName";

    }

    @RequestMapping(value = "SearchByName/editPrice")
    public String editGoodByPrice(@RequestParam("goodId") String id, @RequestParam("goodPrice") String goodPrice)
    {
        goodService.editGoodPrice(goodPrice, id);

        return "searchByName";

    }

    @RequestMapping(value = "SearchByName/editSize")
    public String editGoodBySize(@RequestParam("goodId") String id, @RequestParam("goodSize") String goodPrice, Model model)
    {

        return goodService.eidtGoodSize(goodPrice, id, model);

    }

    @RequestMapping(value = "SearchByName/editStore")
    public String editGoodByStore(@RequestParam("goodId") String id, @RequestParam("goodStore") String goodStore, Model model)
    {

        return goodService.editGoodStore(goodStore, id, model);

    }



    @DeleteMapping(value = "SearchByName/deleteGood")
    public String deleteCurrentGood(@RequestParam("goodId") int id)
    {
        goodService.deleteGood(id);
        return "searchByName";
    }



}
