package com.example.core.services;

import com.example.core.models.TipoUtilizador;
import com.example.core.reps.TipoUtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUtilizadorService {

    private final TipoUtilizadorRepository repository;

    public TipoUtilizadorService(TipoUtilizadorRepository repository) {
        this.repository = repository;
    }

    public List<TipoUtilizador> findAll() {
        return repository.findAll();
    }

    public Optional<TipoUtilizador> findById(Long id) {
        return repository.findById(id);
    }

    public TipoUtilizador save(TipoUtilizador tipo) {
        return repository.save(tipo);
    }

    public TipoUtilizador update(Long id, TipoUtilizador updated) {
        TipoUtilizador tipo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoUtilizador não encontrado com ID: " + id));

        tipo.setNome(updated.getNome());
        tipo.setPermissoes(updated.getPermissoes());

        return repository.save(tipo);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
