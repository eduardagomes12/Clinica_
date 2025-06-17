package com.example.frontoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // nome do ficheiro HTML em templates/home.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registar")
    public String registarPage() {
        return "registar";
    }
}
