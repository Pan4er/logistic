package com.example.logistic.service;

import com.example.logistic.entity.Good;
import com.example.logistic.entity.Store;
import com.example.logistic.repo.GoodRepo;
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
    private StoreRepo storeRepo;
    @Autowired
    private GoodRepo goodRepo;
    @Autowired
    private DbInit dbInit;

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

    public void editStoreName(int id, String newName)
    {
        Store store = storeRepo.findById(id).get();
        store.setStore_name(newName);
        storeRepo.save(store);

    }
    public String editStoreFullness(int id, double newfullness, Model model)
    {
        Store store = storeRepo.findById(id).get();
        if (newfullness < store.getCurrentf())
        {
            model.addAttribute("errorText","Невозможно изменить размер(переполнение)");
            return "Err";
        }
        else
        {
            store.setFullness(newfullness);
            storeRepo.save(store);
            this.renderStoresList(model);
            return "stores";
        }

    }

    public String deleteStore(int storeToDeleteId, int storeToReplaceGoodsId, Model model)
    {
        Store storeToDelete = storeRepo.findById(storeToDeleteId).get();
        Store storesToReplace = storeRepo.findById(storeToReplaceGoodsId).get();

        if (storeToDeleteId == storeToReplaceGoodsId)
        {
            model.addAttribute("errorText", "Склады не должны совпадать");
            return "Err";
        }
        else
        {
            if (storeToDelete.getCurrentf() > (storesToReplace.getFullness() - storesToReplace.getCurrentf()))
            {
                model.addAttribute("errorText", "у выбранного склада будет переполнение");
                return "Err";
            }
            else
            {
                this.replaceGoods(storeToDelete,storesToReplace);
                this.renderStoresList(model);
                dbInit.initCurrentFullness();
                return "stores";
            }
        }



    }

    public void replaceGoods(Store storeToDelete, Store storeToReplace)
    {
        List<Good> goodsToDelete = storeToDelete.getGoods();
        List<Good> goodsToReplace = storeToReplace.getGoods();

        if (goodsToDelete.isEmpty())
        {
            storeRepo.deleteById(storeToDelete.getId());
        }
        else
        {
            goodsToDelete.forEach(delGood -> {
                goodsToReplace.add(delGood);
            });
            storeRepo.save(storeToReplace);
            storeRepo.deleteById(storeToDelete.getId());
        }
    }

    public String addNewStore(String storeName, double storeSize, Model model)
    {
        if (!storeName.isEmpty() && storeSize > 0)
        {
            Store newStore = new Store();
            newStore.setStore_name(storeName);
            newStore.setFullness(storeSize);
            newStore.setCurrentf(0);
            storeRepo.save(newStore);
            dbInit.initCurrentFullness();
            model.addAttribute("success", "Склад добавлен");
            return "succesPost";
        }
        else
        {
            model.addAttribute("errorText","Неправильно заполненны поля");
            return "Err";
        }

    }






}
