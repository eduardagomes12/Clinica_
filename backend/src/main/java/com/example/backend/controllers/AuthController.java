package com.example.backend.controllers;

import com.example.backend.DTO.UtilizadorDTO;
import com.example.core.models.TipoUtilizador;
import com.example.core.models.Utilizador;
import com.example.core.reps.TipoUtilizadorRepository;
import com.example.core.services.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private TipoUtilizadorRepository tipoUtilizadorRepository;

    @PostMapping("/registar")
    public ResponseEntity<?> registar(@RequestBody UtilizadorDTO dto) {
        // Verifica se o email já existe
        if (utilizadorService.encontrarPorEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já registado.");
        }

        // Busca o tipo "Cliente" automaticamente
        TipoUtilizador tipoCliente = tipoUtilizadorRepository.findByNome("Cliente")
                .orElseThrow(() -> new RuntimeException("Tipo 'Cliente' não encontrado."));

        // Cria e guarda o novo utilizador
        Utilizador novo = new Utilizador();
        novo.setNome(dto.getNome());
        novo.setEmail(dto.getEmail());
        novo.setSenha(dto.getSenha()); // ⚠️ Idealmente encriptar a senha
        novo.setTipoUtilizador(tipoCliente);

        Utilizador guardado = utilizadorService.guardar(novo);
        return ResponseEntity.ok(guardado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> dados) {
        String email = dados.get("email");
        String senha = dados.get("senha");

        Optional<Utilizador> encontrado = utilizadorService.encontrarPorEmail(email);

        if (encontrado.isPresent()) {
            if (encontrado.get().getSenha().equals(senha)) {
                return ResponseEntity.ok(encontrado.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}
