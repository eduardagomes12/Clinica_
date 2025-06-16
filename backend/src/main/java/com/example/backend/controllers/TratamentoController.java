package com.example.backend.controllers;

import com.example.backend.DTO.TratamentoDTO;
import com.example.core.models.Tratamento;
import com.example.core.services.TratamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    private final TratamentoService service;

    public TratamentoController(TratamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TratamentoDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TratamentoDTO> create(@RequestBody TratamentoDTO dto) {
        Tratamento t = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.save(t)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamentoDTO> update(@PathVariable Long id, @RequestBody TratamentoDTO dto) {
        dto.setId(id);
        Tratamento updated = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões
    private TratamentoDTO toDTO(Tratamento t) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(t.getId());
        dto.setTipo(t.getTipo());
        dto.setData(t.getData());
        dto.setDescricao(t.getDescricao());
        dto.setStatus(t.getStatus());
        dto.setIdTipoTratamento(t.getTipoTratamento().getId());
        dto.setIdConsulta(t.getConsulta().getId());
        return dto;
    }

    private Tratamento toEntity(TratamentoDTO dto) {
        Tratamento t = new Tratamento();
        t.setId(dto.getId());
        t.setTipo(dto.getTipo());
        t.setData(dto.getData());
        t.setDescricao(dto.getDescricao());
        t.setStatus(dto.getStatus());
        t.setTipoTratamento(service.getTipoTratamentoById(dto.getIdTipoTratamento()));
        t.setConsulta(service.getConsultaById(dto.getIdConsulta()));
        return t;
    }
}
