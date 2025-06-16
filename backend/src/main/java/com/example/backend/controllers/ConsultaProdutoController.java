package com.example.backend.controllers;

import com.example.backend.DTO.ConsultaProdutoDTO;
import com.example.core.models.ConsultaProduto;
import com.example.core.services.ConsultaProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta-produto")
public class ConsultaProdutoController {

    private final ConsultaProdutoService service;

    public ConsultaProdutoController(ConsultaProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaProdutoDTO>> getAll() {
        List<ConsultaProdutoDTO> dtos = service.findAll().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ConsultaProdutoDTO> create(@RequestBody ConsultaProdutoDTO dto) {
        return service.save(dto.getIdConsulta(), dto.getIdProduto())
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{consultaId}/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable Long consultaId, @PathVariable Long produtoId) {
        service.delete(consultaId, produtoId);
        return ResponseEntity.noContent().build();
    }

    private ConsultaProdutoDTO toDTO(ConsultaProduto cp) {
        ConsultaProdutoDTO dto = new ConsultaProdutoDTO();
        dto.setIdConsulta(cp.getConsulta().getId());
        dto.setIdProduto(cp.getProduto().getId());
        return dto;
    }
}
