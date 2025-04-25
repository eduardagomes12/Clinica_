package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Linhafatura;
import com.example.clinicadesktop.reps.LinhaFaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinhaFaturaService {

    @Autowired
    private LinhaFaturaRepository repository;

    public List<Linhafatura> findAll() {
        return repository.findAll();
    }

    public Linhafatura findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Linhafatura save(Linhafatura linhaFatura) {
        return repository.save(linhaFatura);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
