package com.example.backend.controllers;

import com.example.backend.DTO.ConsultaProdutoDTO;
import com.example.backend.services.ConsultaProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta-produto")
public class ConsultaProdutoController {

    private final ConsultaProdutoService service;

    public ConsultaProdutoController(ConsultaProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaProdutoDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ConsultaProdutoDTO> create(@RequestBody ConsultaProdutoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{consultaId}/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable Long consultaId, @PathVariable Long produtoId) {
        service.delete(consultaId, produtoId);
        return ResponseEntity.noContent().build();
    }
}
