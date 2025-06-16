package com.example.core.services;

import com.example.core.models.Produto;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    public Produto save(Produto p) {
        return repository.save(p);
    }

    public Produto update(Long id, Produto p) {
        Produto existing = repository.findById(id).orElseThrow();
        existing.setNome(p.getNome());
        existing.setPreco(p.getPreco());
        existing.setStock(p.getStock());
        return repository.save(existing);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
