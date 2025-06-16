package com.example.backend.controllers;

import com.example.backend.DTO.LinhaFaturaDTO;
import com.example.core.models.Fatura;
import com.example.core.models.LinhaFatura;
import com.example.core.services.LinhaFaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/linhas-fatura")
public class LinhaFaturaController {

    private final LinhaFaturaService service;

    public LinhaFaturaController(LinhaFaturaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LinhaFaturaDTO>> getAll() {
        List<LinhaFaturaDTO> list = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinhaFaturaDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LinhaFaturaDTO> create(@RequestBody LinhaFaturaDTO dto) {
        LinhaFatura lf = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.save(lf)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinhaFaturaDTO> update(@PathVariable Long id, @RequestBody LinhaFaturaDTO dto) {
        LinhaFatura lf = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.update(id, lf)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões

    private LinhaFaturaDTO toDTO(LinhaFatura lf) {
        LinhaFaturaDTO dto = new LinhaFaturaDTO();
        dto.setId(lf.getId());
        dto.setDescricao(lf.getDescricao());
        dto.setPrecoUnit(lf.getPrecoUnit());
        dto.setQtd(lf.getQtd());
        dto.setTotal(lf.getTotal());
        dto.setIdFatura(lf.getFatura().getId());
        return dto;
    }

    private LinhaFatura toEntity(LinhaFaturaDTO dto) {
        LinhaFatura lf = new LinhaFatura();
        lf.setId(dto.getId());
        lf.setDescricao(dto.getDescricao());
        lf.setPrecoUnit(dto.getPrecoUnit());
        lf.setQtd(dto.getQtd());
        lf.setTotal(dto.getTotal());
        Fatura fatura = service.getFaturaById(dto.getIdFatura());
        lf.setFatura(fatura);
        return lf;
    }
}
