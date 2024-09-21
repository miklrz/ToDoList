package ru.hxastur.todolist.controllers;

import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.hxastur.todolist.models.Author;
import ru.hxastur.todolist.models.Authority;
import ru.hxastur.todolist.repositories.AuthorityRepository;
import ru.hxastur.todolist.service.AuthorService;

import java.util.List;
import java.util.Set;

@Controller
public class LoginController {
    private final AuthorService authorService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthorityRepository authorityRepository;

    public LoginController(AuthorService authorService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityRepository authorityRepository){
        this.authorService = authorService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "login/home";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("author") Author author){
        return "login/register";
    }

    @PostMapping("/register")
    @Transactional
    public String registration(@ModelAttribute Author author){
        String encodedPassword = bCryptPasswordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);

        Authority authority = new Authority("ROLE_USER",author);
        author.setAuthorities(Set.of(authority));

        authorService.saveAuthor(author);
        authorityRepository.save(authority);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String defaultPage(){
        return "login/defaultPage";
    }
}
