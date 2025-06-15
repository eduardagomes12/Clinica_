package com.example.backend.services;

import com.example.backend.DTO.TipoTratamentoDTO;
import com.example.core.models.TipoTratamento;
import com.example.core.reps.TipoTratamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoTratamentoService {

    private final TipoTratamentoRepository repository;

    public TipoTratamentoService(TipoTratamentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoTratamentoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TipoTratamentoDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public TipoTratamentoDTO save(TipoTratamentoDTO dto) {
        TipoTratamento tipo = toEntity(dto);
        return toDTO(repository.save(tipo));
    }

    public TipoTratamentoDTO update(Long id, TipoTratamentoDTO dto) {
        TipoTratamento tipo = repository.findById(id).orElseThrow();
        tipo.setNome(dto.getNome());
        tipo.setDescricao(dto.getDescricao());
        return toDTO(repository.save(tipo));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Conversões
    private TipoTratamentoDTO toDTO(TipoTratamento tipo) {
        TipoTratamentoDTO dto = new TipoTratamentoDTO();
        dto.setId(tipo.getId());
        dto.setNome(tipo.getNome());
        dto.setDescricao(tipo.getDescricao());
        return dto;
    }

    private TipoTratamento toEntity(TipoTratamentoDTO dto) {
        TipoTratamento tipo = new TipoTratamento();
        tipo.setId(dto.getId());
        tipo.setNome(dto.getNome());
        tipo.setDescricao(dto.getDescricao());
        return tipo;
    }
}
