package com.example.backend.controllers;

import com.example.backend.DTO.PagamentoDTO;
import com.example.core.models.Fatura;
import com.example.core.models.Pagamento;
import com.example.core.services.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PagamentoDTO> getAll() {
        return service.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> create(@RequestBody PagamentoDTO dto) {
        Pagamento p = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.save(p)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> update(@PathVariable Long id, @RequestBody PagamentoDTO dto) {
        Pagamento p = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.update(id, p)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Conversões

    private PagamentoDTO toDTO(Pagamento p) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(p.getId());
        dto.setValor(p.getValor());
        dto.setValorTotal(p.getValorTotal());
        dto.setData(p.getData());

        if (p.getFatura() != null) {
            dto.setIdFatura(p.getFatura().getId());
        }

        if (p.getTipoPagamento() != null)
            dto.setIdTipoPagamento(p.getTipoPagamento().getId());

        if (p.getConsulta() != null)
            dto.setIdConsulta(p.getConsulta().getId());

        return dto;
    }

    private Pagamento toEntity(PagamentoDTO dto) {
        Pagamento p = new Pagamento();
        p.setId(dto.getId());
        p.setValor(dto.getValor());
        p.setValorTotal(dto.getValorTotal());
        p.setData(dto.getData());

        p.setFatura(service.getFaturaById(dto.getIdFatura()));

        if (dto.getIdTipoPagamento() != null)
            p.setTipoPagamento(service.getTipoPagamentoById(dto.getIdTipoPagamento()));

        if (dto.getIdConsulta() != null)
            p.setConsulta(service.getConsultaById(dto.getIdConsulta()));

        return p;
    }

}
