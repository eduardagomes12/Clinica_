package com.example.core.services;

import com.example.core.models.TipoTratamento;
import com.example.core.reps.TipoTratamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTratamentoService {

    private final TipoTratamentoRepository repository;

    public TipoTratamentoService(TipoTratamentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoTratamento> findAll() {
        return repository.findAll();
    }

    public Optional<TipoTratamento> findById(Long id) {
        return repository.findById(id);
    }

    public TipoTratamento save(TipoTratamento tipo) {
        return repository.save(tipo);
    }

    public TipoTratamento update(Long id, TipoTratamento updated) {
        TipoTratamento tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoTratamento não encontrado com ID: " + id));

        tipo.setNome(updated.getNome());
        tipo.setDescricao(updated.getDescricao());

        return repository.save(tipo);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
