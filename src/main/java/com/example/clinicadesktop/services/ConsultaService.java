package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Consulta;
import com.example.clinicadesktop.reps.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    public List<Consulta> findAll() {
        return repository.findAll();
    }

    public Consulta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

