package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Tipoutilizador;
import com.example.clinicadesktop.reps.TipoUtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUtilizadorService {

    @Autowired
    private TipoUtilizadorRepository repository;

    public List<Tipoutilizador> findAll() {
        return repository.findAll();
    }

    public Tipoutilizador findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tipoutilizador save(Tipoutilizador tipoUtilizador) {
        return repository.save(tipoUtilizador);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
