package com.example.frontoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoricoController {

    @GetMapping("/historico")
    public String mostrarHistorico() {
        return "historico"; // corresponde ao nome do arquivo: historico.html
    }
}
