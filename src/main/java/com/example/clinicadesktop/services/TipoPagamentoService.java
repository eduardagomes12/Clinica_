package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Tipopagamento;
import com.example.clinicadesktop.reps.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository repository;

    public List<Tipopagamento> findAll() {
        return repository.findAll();
    }

    public Tipopagamento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tipopagamento save(Tipopagamento tipoPagamento) {
        return repository.save(tipoPagamento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
