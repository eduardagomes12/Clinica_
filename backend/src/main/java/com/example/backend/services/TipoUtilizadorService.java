package com.example.backend.services;

import com.example.backend.DTO.TipoUtilizadorDTO;
import com.example.core.models.TipoUtilizador;
import com.example.core.reps.TipoUtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoUtilizadorService {

    private final TipoUtilizadorRepository repository;

    public TipoUtilizadorService(TipoUtilizadorRepository repository) {
        this.repository = repository;
    }

    public List<TipoUtilizadorDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TipoUtilizadorDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public TipoUtilizadorDTO save(TipoUtilizadorDTO dto) {
        TipoUtilizador tipo = toEntity(dto);
        return toDTO(repository.save(tipo));
    }

    public TipoUtilizadorDTO update(Long id, TipoUtilizadorDTO dto) {
        TipoUtilizador tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoUtilizador não encontrado com ID: " + id));

        tipo.setNome(dto.getNome());
        tipo.setPermissoes(dto.getPermissoes());

        return toDTO(repository.save(tipo));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Conversões
    private TipoUtilizadorDTO toDTO(TipoUtilizador tipo) {
        TipoUtilizadorDTO dto = new TipoUtilizadorDTO();
        dto.setId(tipo.getId());
        dto.setNome(tipo.getNome());
        dto.setPermissoes(tipo.getPermissoes());
        return dto;
    }

    private TipoUtilizador toEntity(TipoUtilizadorDTO dto) {
        TipoUtilizador tipo = new TipoUtilizador();
        tipo.setId(dto.getId());
        tipo.setNome(dto.getNome());
        tipo.setPermissoes(dto.getPermissoes());
        return tipo;
    }
}
