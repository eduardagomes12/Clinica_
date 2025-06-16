package com.example.core.services;

import com.example.core.models.TipoPagamento;
import com.example.core.reps.TipoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {

    private final TipoPagamentoRepository repository;

    public TipoPagamentoService(TipoPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoPagamento> findAll() {
        return repository.findAll();
    }

    public Optional<TipoPagamento> findById(Long id) {
        return repository.findById(id);
    }

    public TipoPagamento save(TipoPagamento entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
