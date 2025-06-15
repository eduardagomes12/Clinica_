package com.example.backend.controllers;

import com.example.backend.DTO.TipoTratamentoDTO;
import com.example.backend.services.TipoTratamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-tratamento")
public class TipoTratamentoController {

    private final TipoTratamentoService service;

    public TipoTratamentoController(TipoTratamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoTratamentoDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTratamentoDTO> getById(@PathVariable Long id) {
        TipoTratamentoDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TipoTratamentoDTO> create(@RequestBody TipoTratamentoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTratamentoDTO> update(@PathVariable Long id, @RequestBody TipoTratamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
