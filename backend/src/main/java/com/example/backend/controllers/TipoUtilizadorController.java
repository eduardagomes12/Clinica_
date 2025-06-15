package com.example.backend.controllers;

import com.example.backend.DTO.TipoUtilizadorDTO;
import com.example.backend.services.TipoUtilizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-utilizador")
public class TipoUtilizadorController {

    private final TipoUtilizadorService service;

    public TipoUtilizadorController(TipoUtilizadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoUtilizadorDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUtilizadorDTO> getById(@PathVariable Long id) {
        TipoUtilizadorDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TipoUtilizadorDTO> create(@RequestBody TipoUtilizadorDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUtilizadorDTO> update(@PathVariable Long id, @RequestBody TipoUtilizadorDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
