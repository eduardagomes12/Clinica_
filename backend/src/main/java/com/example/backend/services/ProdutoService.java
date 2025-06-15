package com.example.backend.services;

import com.example.backend.DTO.ProdutoDTO;
import com.example.core.models.Produto;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> findById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public ProdutoDTO save(ProdutoDTO dto) {
        Produto p = toEntity(dto);
        return toDTO(repository.save(p));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // Conversão
    private ProdutoDTO toDTO(Produto p) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setPreco(p.getPreco());
        dto.setStock(p.getStock());
        return dto;
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setStock(dto.getStock());
        return p;
    }
}
