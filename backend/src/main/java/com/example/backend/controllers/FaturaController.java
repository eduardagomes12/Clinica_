package com.example.backend.controllers;

import com.example.backend.DTO.FaturaDTO;
import com.example.core.models.Fatura;
import com.example.core.services.FaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faturas")
public class FaturaController {

    private final FaturaService service;

    public FaturaController(FaturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<FaturaDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FaturaDTO> create(@RequestBody FaturaDTO dto) {
        Fatura saved = service.save(fromDTO(dto), dto.getIdTipoPagamento());
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturaDTO> update(@PathVariable Long id, @RequestBody FaturaDTO dto) {
        dto.setId(id);
        Fatura saved = service.save(fromDTO(dto), dto.getIdTipoPagamento());
        return ResponseEntity.ok(toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // === Conversões ===

    private FaturaDTO toDTO(Fatura f) {
        FaturaDTO dto = new FaturaDTO();
        dto.setId(f.getId());
        dto.setNum(f.getNum());
        dto.setData(f.getData());
        dto.setValorTotal(f.getValorTotal());
        dto.setIdTipoPagamento(f.getTipoPagamento().getId());
        dto.setDescricaoTipoPagamento(f.getTipoPagamento().getDescricao());
        return dto;
    }

    private Fatura fromDTO(FaturaDTO dto) {
        Fatura f = new Fatura();
        f.setId(dto.getId());
        f.setNum(dto.getNum());
        f.setData(dto.getData());
        f.setValorTotal(dto.getValorTotal());
        return f;
    }
}
