package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Fatura;
import com.example.clinicadesktop.reps.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository repository;

    public List<Fatura> findAll() {
        return repository.findAll();
    }

    public Fatura findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Fatura save(Fatura fatura) {
        return repository.save(fatura);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
