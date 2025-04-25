package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Tipotratamento;
import com.example.clinicadesktop.reps.TipoTratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoTratamentoService {

    @Autowired
    private TipoTratamentoRepository repository;

    public List<Tipotratamento> findAll() {
        return repository.findAll();
    }

    public Tipotratamento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tipotratamento save(Tipotratamento tipoTratamento) {
        return repository.save(tipoTratamento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
