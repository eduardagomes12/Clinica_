package com.example.backend.controllers;

import com.example.backend.DTO.UtilizadorDTO;
import com.example.core.models.Utilizador;
import com.example.core.services.UtilizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    private final UtilizadorService service;

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UtilizadorDTO>> getAll() {
        List<UtilizadorDTO> dtos = service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilizadorDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UtilizadorDTO> create(@RequestBody UtilizadorDTO dto) {
        Utilizador utilizador = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.save(utilizador)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilizadorDTO> update(@PathVariable Long id, @RequestBody UtilizadorDTO dto) {
        dto.setId(id);
        Utilizador atualizado = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões
    private UtilizadorDTO toDTO(Utilizador u) {
        UtilizadorDTO dto = new UtilizadorDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setSenha(u.getSenha());
        if (u.getTipoUtilizador() != null) {
            dto.setIdTipoUtilizador(u.getTipoUtilizador().getId());
            dto.setNomeTipoUtilizador(u.getTipoUtilizador().getNome());
        }
        return dto;
    }

    private Utilizador toEntity(UtilizadorDTO dto) {
        Utilizador u = new Utilizador();
        u.setId(dto.getId());
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenha(dto.getSenha());
        if (dto.getIdTipoUtilizador() != null) {
            u.setTipoUtilizador(service.getTipoUtilizadorById(dto.getIdTipoUtilizador()));
        }
        return u;
    }
}
