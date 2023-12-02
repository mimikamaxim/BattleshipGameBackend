package jcource.battleship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        return "security/login";
    }

    @GetMapping("/register")
    public String register(){
        return "security/register";
    }

    @PostMapping("/register")
    public String registerUser(){
        return "security/login";
    }
}
