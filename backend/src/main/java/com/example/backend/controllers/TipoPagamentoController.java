package com.example.backend.controllers;

import com.example.backend.DTO.TipoPagamentoDTO;
import com.example.core.models.TipoPagamento;
import com.example.core.services.TipoPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-pagamento")
public class TipoPagamentoController {

    private final TipoPagamentoService service;

    public TipoPagamentoController(TipoPagamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os tipos de pagamento")
    @GetMapping
    public List<TipoPagamentoDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Buscar tipo de pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamentoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar ou atualizar tipo de pagamento")
    @PostMapping
    public ResponseEntity<TipoPagamentoDTO> create(@RequestBody TipoPagamentoDTO dto) {
        TipoPagamento entity = toEntity(dto);
        TipoPagamento saved = service.save(entity);
        return ResponseEntity.ok(toDTO(saved));
    }

    @Operation(summary = "Eliminar tipo de pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Converters
    private TipoPagamentoDTO toDTO(TipoPagamento entity) {
        TipoPagamentoDTO dto = new TipoPagamentoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    private TipoPagamento toEntity(TipoPagamentoDTO dto) {
        TipoPagamento entity = new TipoPagamento();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}
