package com.example.logistic.controller;

import com.example.logistic.service.DbInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @Autowired
    private DbInit dbInit;
    @GetMapping("/")
    public String init(){
        dbInit.initCurrentFullness();
        return "index";
    }
}
