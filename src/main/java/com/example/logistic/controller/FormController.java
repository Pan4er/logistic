package com.example.logistic.controller;

import com.example.logistic.entity.Good;
import com.example.logistic.repo.GoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class FormController {
    @Autowired
    private GoodRepo goodRepo;

    @GetMapping(value = "addGood")
    public String addGoodRequest() {
        return "addGood";
    }
    @GetMapping(value = "deleteGood")
    public String deleteGoodRequest() {
        return "deleteGood";
    }
    @GetMapping(value = "editGood")
    public String editGoodRequest() {
        return "editGood";
    }

    @PostMapping(value = "addGood")
    public String FormCreate(@RequestParam("goodName") String name,
                           @RequestParam("goodPrice") String price,
                           @RequestParam("goodOrder") String order)
    {

        if (!name.isEmpty() && !price.isEmpty() && !order.isEmpty()){
            Good newGood = new Good();
            newGood.setName(name);
            newGood.setPrice(Integer.parseInt(price));
            newGood.setStore(Integer.parseInt(order));
            goodRepo.save(newGood);
            return "succesPost";
        }
        else
        {
            return "Error";
        }

    }

    @DeleteMapping(value = "deleteGood")
    public String DeleteForm(@RequestParam("id") int id){


        try {
            goodRepo.deleteById(id);
            return "succesPost";
        }
        catch(Exception e) {
            return "Error";
        }
    }




    @PatchMapping(value = "editGood")
    public String PatchForm(@RequestParam("id") int id,
                            @RequestParam("newName") String newName,
                            @RequestParam("newPrice") String newPrice,
                            @RequestParam("newOrder") String newOrder
    ){
        try {

            Good editedGood = new Good();
            editedGood = goodRepo.findById(id).get();

            if (newName.isEmpty()) {
                editedGood.setName(editedGood.getName());
            } else {
                editedGood.setName(newName);
            }

            if (newPrice.isEmpty()) {
                editedGood.setPrice(editedGood.getPrice());
            } else {
                editedGood.setPrice(Integer.parseInt(newPrice));
            }

            if (newOrder.isEmpty()) {
                editedGood.setStore(editedGood.getStore());
            } else {
                editedGood.setStore(Integer.parseInt(newOrder));
            }

            goodRepo.save(editedGood);

            return "succesPost";
        }
        catch(Exception e) {
            return "Error";
        }

    }
}
