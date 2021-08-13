package com.example.logistic.service;

import antlr.ASTNULLType;
import com.example.logistic.entity.Good;
import com.example.logistic.entity.Store;
import com.example.logistic.repo.GoodRepo;
import com.example.logistic.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Service
public class GoodService {

    @Autowired
    private GoodRepo gr;
    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private DbInit dbInit;


    public String getAll(Model model)
    {
        model.addAttribute("goods", ArraysOperations.toList(gr.findAll()));
        List<Store> stores = ArraysOperations.toList(storeRepo.findAll());
        model.addAttribute("storesList",stores);
        return "goods";

    }



    public String addNewGood(String StoreId, String goodName, double goodSize, String goodPrice, Model model)
    {

        dbInit.initCurrentFullness();
        Store currentStore = storeRepo.findById(Integer.parseInt(StoreId)).get();


        if (!StoreId.isEmpty() && !goodName.isEmpty() && !Double.isNaN(goodSize) && !goodPrice.isEmpty())
        {
            if (goodSize <= 0)
            {
                model.addAttribute("errorText","размер товара должен быть больше нуля");
                return "Err";
            }

            if (currentStore.getCurrentf() + goodSize > currentStore.getFullness())
            {
                model.addAttribute("errorText","На выбранном складе недостаточно места");
                return "Err";
            }
            Good newGood = new Good();
            newGood.setName(goodName);
            newGood.setPrice(Integer.parseInt(goodPrice));
            newGood.setSize(goodSize);
            newGood.setStore(Integer.parseInt(StoreId));
            gr.save(newGood);
            model.addAttribute("success","Товар добавлен");
            return "succesPost";
        }
        else
        {
            model.addAttribute("errorText", "Заполните все поля");
            return "Err";
        }

    }



    public void deleteGood(int id)
    {
        try {
            gr.deleteById(id);

        }
        catch(Exception e) {
            e.getMessage();
        }
    }

    public String renderGoodsSearch(Model model,String keyword) {
        if (keyword != null) {
           List<Good> goods = gr.search(keyword);
           List<Store> stores = ArraysOperations.toList(storeRepo.findAll());
            model.addAttribute("goods", goods);
            model.addAttribute("stores", stores);
            model.addAttribute("keyword", keyword);
        }
        else
        {
            List<Good> goods = gr.search("");
            List<Store> stores = ArraysOperations.toList(storeRepo.findAll());
            model.addAttribute("goods", goods);
            model.addAttribute("stores", stores);
            model.addAttribute("keyword", "");
        }
        return "searchByName";
    }

    public void editGoodName(String goodName, String goodId)
    {
        Good good = gr.findById(Integer.parseInt(goodId)).get();
        good.setName(goodName);
        gr.save(good);
    }

    public void editGoodPrice(String goodPrice, String goodId)
    {
        Good good = gr.findById(Integer.parseInt(goodId)).get();
        good.setPrice(Integer.parseInt(goodPrice));
        gr.save(good);
    }

    public String eidtGoodSize(String goodSize, String goodId, Model model)
    {

        Good good = gr.findById(Integer.parseInt(goodId)).get();
        Store currentStore = storeRepo.findById(good.getStore()).get();
        if (Double.parseDouble(goodSize) <= 0)
        {
            model.addAttribute("errorText", "Размер товара должен быть больше нуля");
            return "Err";
        }
        else
        {
            if (Double.parseDouble(goodSize) < good.getSize())
            {
                good.setSize(Double.parseDouble(goodSize));
                gr.save(good);
                dbInit.initCurrentFullness();
                return "searchByName";
            }
            else
            {
                if (currentStore.getCurrentf() + Double.parseDouble(goodSize) <= currentStore.getFullness())
                {
                    good.setSize(Double.parseDouble(goodSize));
                    gr.save(good);
                    dbInit.initCurrentFullness();
                    return "searchByName";
                }
                else
                {
                    model.addAttribute("errorText","Невозможно изменить размер(Переполнение склада)");
                    return "Err";
                }
            }
        }



    }

    public String editGoodStore(String goodStore, String goodId, Model model) {

        Good currentGood = gr.findById(Integer.parseInt(goodId)).get();

        if (storeRepo.existsById(Integer.parseInt(goodStore))) {
            Store currentStore = storeRepo.findById(Integer.parseInt(goodStore)).get();
            if (currentStore.getCurrentf() + currentGood.getSize() <= currentStore.getFullness()) {
                currentGood.setStore(Integer.parseInt(goodStore));
                gr.save(currentGood);
                dbInit.initCurrentFullness();
                return "searchByName";
            } else {
                model.addAttribute("errorText", "Переполнение склада");
                return "Err";
            }
        }
        else
        {
            model.addAttribute("errorText","Такого склада нет");
            return "Err";
        }

    }





}
