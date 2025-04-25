package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Utilizador;
import com.example.clinicadesktop.reps.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository repository;

    public List<Utilizador> findAll() {
        return repository.findAll();
    }

    public Utilizador findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Utilizador save(Utilizador utilizador) {
        return repository.save(utilizador);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
