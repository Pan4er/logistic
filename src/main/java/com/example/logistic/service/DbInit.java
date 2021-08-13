package com.example.logistic.service;

import com.example.logistic.entity.Good;
import com.example.logistic.entity.Store;
import com.example.logistic.repo.GoodRepo;
import com.example.logistic.repo.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DbInit {
    @Autowired
    private StoreRepo storeRepo;


    public void initCurrentFullness(){
        List<Store> stores = ArraysOperations.toList(storeRepo.findAll());

        stores.forEach(store -> {
            if (store.getGoods() != null)
            {
                store.setCurrentf(this.countGoodsSize(store.getGoods()));
            }
            else
            {
                store.setCurrentf(0);
            }
        });
        storeRepo.saveAll(stores);

    }

    private double countGoodsSize(List<Good> goodsOnCurrentStore)
    {
        double size = 0;
        for (int i = 0; i < goodsOnCurrentStore.size(); i++)
        {
            size += goodsOnCurrentStore.get(i).getSize();
        }
        return size;
    }
}
