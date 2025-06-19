package com.example.frontoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeusAnimaisController {

    @GetMapping("/meusAnimais")
    public String meusAnimais() {
        return "meusAnimais"; // refere-se ao ficheiro meusAnimais.html na pasta /templates
    }
}
