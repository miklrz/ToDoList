package ru.hxastur.todolist.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.service.AuthorService;

@Controller
public class LoginController {
    private final AuthorService authorService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(AuthorService authorService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.authorService = authorService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("author") Author author){
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute Author author){
        String encodedPassword = bCryptPasswordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);
        authorService.saveAuthor(author);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String defaultPage(){
        return "defaultPage";
    }
}
