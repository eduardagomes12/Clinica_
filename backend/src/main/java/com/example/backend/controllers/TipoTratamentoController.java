package com.example.backend.controllers;

import com.example.backend.DTO.TipoTratamentoDTO;
import com.example.core.models.TipoTratamento;
import com.example.core.services.TipoTratamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-tratamento")
public class TipoTratamentoController {

    private final TipoTratamentoService service;

    public TipoTratamentoController(TipoTratamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoTratamentoDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTratamentoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoTratamentoDTO> create(@RequestBody TipoTratamentoDTO dto) {
        TipoTratamento saved = service.save(toEntity(dto));
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTratamentoDTO> update(@PathVariable Long id, @RequestBody TipoTratamentoDTO dto) {
        TipoTratamento updated = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversores
    private TipoTratamentoDTO toDTO(TipoTratamento tipo) {
        TipoTratamentoDTO dto = new TipoTratamentoDTO();
        dto.setId(tipo.getId());
        dto.setNome(tipo.getNome());
        dto.setDescricao(tipo.getDescricao());
        dto.setValor(tipo.getValor());
        return dto;
    }

    private TipoTratamento toEntity(TipoTratamentoDTO dto) {
        TipoTratamento tipo = new TipoTratamento();
        tipo.setNome(dto.getNome());
        tipo.setDescricao(dto.getDescricao());
        tipo.setValor(dto.getValor());
        return tipo;
    }

}
