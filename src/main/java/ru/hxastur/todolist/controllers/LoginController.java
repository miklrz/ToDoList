package ru.hxastur.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
}
