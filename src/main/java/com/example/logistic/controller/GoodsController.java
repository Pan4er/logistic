package com.example.logistic.controller;

import com.example.logistic.entity.Good;
import com.example.logistic.repo.GoodRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.rmi.ServerException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class GoodsController {
    //GoodsService gs = new GoodsService();
    @Autowired
    private GoodRepo goodRepo;


    @GetMapping("goods")
    public String viewAllGoods(Model model)
    {


        model.addAttribute("goods", toList(goodRepo.findAll()));
        return "goods";
    }


    public static <E> List<E> toList(Iterable<E> iterable) {
        if(iterable instanceof List) {
            return (List<E>) iterable;
        }
        ArrayList<E> list = new ArrayList<E>();
        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }
        return list;
    }


}
