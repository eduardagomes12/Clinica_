package com.example.backend.services;

import com.example.backend.DTO.TipoPagamentoDTO;
import com.example.core.models.TipoPagamento;
import com.example.core.reps.TipoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoPagamentoService {

    private final TipoPagamentoRepository repository;

    public TipoPagamentoService(TipoPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoPagamentoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<TipoPagamentoDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public TipoPagamentoDTO save(TipoPagamentoDTO dto) {
        TipoPagamento entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

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
