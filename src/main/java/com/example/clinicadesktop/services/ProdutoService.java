package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Produto;
import com.example.clinicadesktop.reps.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
