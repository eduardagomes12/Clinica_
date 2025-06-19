package com.example.frontoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarioController {

    @GetMapping("/calendario")
    public String mostrarCalendario() {
        return "calendario"; // retorna o HTML: calendario.html
    }
}
