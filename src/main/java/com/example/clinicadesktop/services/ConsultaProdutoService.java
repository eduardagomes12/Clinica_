package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Consultaproduto; // Nome corrigido
import com.example.clinicadesktop.reps.ConsultaProdutoRepository; // Nome corrigido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaProdutoService { // Nome corrigido

    @Autowired
    private ConsultaProdutoRepository repository; // Nome corrigido

    public List<Consultaproduto> findAll() { // Nome corrigido
        return repository.findAll();
    }

    public Consultaproduto findById(Long id) { // Nome corrigido
        return repository.findById(id).orElse(null);
    }

    public Consultaproduto save(Consultaproduto consultaProduto) { // Nome corrigido
        return repository.save(consultaProduto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
