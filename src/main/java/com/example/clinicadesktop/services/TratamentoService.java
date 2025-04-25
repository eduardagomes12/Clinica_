package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Tratamento;
import com.example.clinicadesktop.reps.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository repository;

    public List<Tratamento> findAll() {
        return repository.findAll();
    }

    public Tratamento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tratamento save(Tratamento tratamento) {
        return repository.save(tratamento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
