package com.example.backend.controllers;

import com.example.backend.DTO.TipoUtilizadorDTO;
import com.example.core.models.TipoUtilizador;
import com.example.core.services.TipoUtilizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-utilizador")
public class TipoUtilizadorController {

    private final TipoUtilizadorService service;

    public TipoUtilizadorController(TipoUtilizadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoUtilizadorDTO>> getAll() {
        List<TipoUtilizadorDTO> list = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUtilizadorDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoUtilizadorDTO> create(@RequestBody TipoUtilizadorDTO dto) {
        TipoUtilizador saved = service.save(toEntity(dto));
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUtilizadorDTO> update(@PathVariable Long id, @RequestBody TipoUtilizadorDTO dto) {
        TipoUtilizador updated = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões DTO ↔ Entity
    private TipoUtilizadorDTO toDTO(TipoUtilizador tipo) {
        TipoUtilizadorDTO dto = new TipoUtilizadorDTO();
        dto.setId(tipo.getId());
        dto.setNome(tipo.getNome());
        dto.setPermissoes(tipo.getPermissoes());
        return dto;
    }

    private TipoUtilizador toEntity(TipoUtilizadorDTO dto) {
        TipoUtilizador tipo = new TipoUtilizador();
        tipo.setId(dto.getId());
        tipo.setNome(dto.getNome());
        tipo.setPermissoes(dto.getPermissoes());
        return tipo;
    }
}
