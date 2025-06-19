package com.example.frontoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarcarConsultaController {

    @GetMapping("/marcarConsulta")
    public String mostrarPagina() {
        return "marcarConsulta";
    }
}
