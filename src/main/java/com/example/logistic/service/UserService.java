package com.example.logistic.service;

import com.example.logistic.entity.Usr;
import com.example.logistic.repo.UsrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UsrRepo usrRepo;

    public String authorization(String login, String password, Model model, HttpSession session)
    {
        if (usrRepo.getUsrByLogin(login) != null)
        {
            if (usrRepo.getUsrByLogin(login).getPassword().equals(password))
            {
                session.setAttribute("login", login);
                return "redirect:/";
            }
            else
            {
                model.addAttribute("errorText","Неверный пароль");
                return "err";
            }
        }
        else
        {
            model.addAttribute("errorText","Такого пользователя нет");
            return "err";
        }
    }

    public String registration(String login, String password, String confirmPassword, Model model)
    {
        if (password.equals(confirmPassword))
        {
            Usr newUser = new Usr();
            newUser.setLogin(login);
            newUser.setPassword(password);
            usrRepo.save(newUser);
            return "redirect:/";
        }
        else
        {
            model.addAttribute("errorText", "Пароли не совпадают");
            return "err";
        }
    }
}
