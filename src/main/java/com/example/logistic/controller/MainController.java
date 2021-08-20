package com.example.logistic.controller;

import com.example.logistic.entity.Usr;
import com.example.logistic.service.DbInit;
import com.example.logistic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@SessionAttributes(value = "user")
@Controller
public class MainController {
    @Autowired
    private DbInit dbInit;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String init(HttpSession session){
        dbInit.initCurrentFullness();
        if (session.getAttribute("login") == null)
        {
            return "auth";
        }
        else
        {
            return "redirect:goods";

        }

    }
    @GetMapping("reg")
    public String getRegPage()
    {
        return "reg";
    }

    @GetMapping("auth")
    public String getAuthPage()
    {
        return "auth";
    }

    @GetMapping("user")
    public String getUserPage()
    {
        return "user";
    }

    @PostMapping("reg")
    public String reg(@RequestParam("login") String login,
                             @RequestParam("password") String password,
                             @RequestParam("confirmPassword") String confirmPassword,
                             Model model)
    {
        return userService.registration(login,password,confirmPassword,model);
    }

    @PostMapping("auth")
    public String auth(HttpSession session, @RequestParam("login") String login, @RequestParam("password") String password, Model model)
    {
        return userService.authorization(login,password,model,session);
    }



}
