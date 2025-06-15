package com.example.backend.controllers;

import com.example.backend.DTO.TipoPagamentoDTO;
import com.example.backend.services.TipoPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.findAll();
    }

    @Operation(summary = "Buscar tipo de pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamentoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar ou atualizar tipo de pagamento")
    @PostMapping
    public ResponseEntity<TipoPagamentoDTO> create(@RequestBody TipoPagamentoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "Eliminar tipo de pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
