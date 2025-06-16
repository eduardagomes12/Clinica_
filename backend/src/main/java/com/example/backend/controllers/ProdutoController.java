package com.example.backend.controllers;

import com.example.backend.DTO.ProdutoDTO;
import com.example.core.models.Produto;
import com.example.core.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO dto) {
        Produto p = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.save(p)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto p = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.update(id, p)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões

    private ProdutoDTO toDTO(Produto p) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPreco(p.getPreco());
        dto.setStock(p.getStock());
        return dto;
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setStock(dto.getStock());
        return p;
    }
}
