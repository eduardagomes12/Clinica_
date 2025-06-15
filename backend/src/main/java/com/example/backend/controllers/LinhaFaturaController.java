package com.example.backend.controllers;

import com.example.backend.DTO.LinhaFaturaDTO;
import com.example.backend.services.LinhaFaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linhas-fatura")
public class LinhaFaturaController {

    private final LinhaFaturaService service;

    public LinhaFaturaController(LinhaFaturaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LinhaFaturaDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinhaFaturaDTO> getById(@PathVariable Long id) {
        LinhaFaturaDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LinhaFaturaDTO> create(@RequestBody LinhaFaturaDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinhaFaturaDTO> update(@PathVariable Long id, @RequestBody LinhaFaturaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
