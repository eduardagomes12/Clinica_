package com.example.backend.controllers;

import com.example.backend.DTO.TratamentoDTO;
import com.example.backend.services.TratamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    private final TratamentoService service;

    public TratamentoController(TratamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TratamentoDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> getById(@PathVariable Long id) {
        TratamentoDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TratamentoDTO> create(@RequestBody TratamentoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamentoDTO> update(@PathVariable Long id, @RequestBody TratamentoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
