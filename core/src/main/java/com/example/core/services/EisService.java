package com.example.core.services;

import com.example.core.models.Eis;
import com.example.core.models.Produto;
import com.example.core.reps.EisRepository;
import com.example.core.reps.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EisService {

    private final EisRepository eisRepository;
    private final ProdutoRepository produtoRepository;

    public EisService(EisRepository eisRepository, ProdutoRepository produtoRepository) {
        this.eisRepository = eisRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Eis> findAll() {
        return eisRepository.findAll();
    }

    public Optional<Eis> findById(Long id) {
        return eisRepository.findById(id);
    }

    public Eis save(Eis eis, Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        eis.setProduto(produto);
        return eisRepository.save(eis);
    }

    public Eis update(Long id, Eis novo, Long produtoId) {
        Eis existente = eisRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("EIS com id " + id + " não encontrado"));
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NoSuchElementException("Produto com id " + produtoId + " não encontrado"));

        existente.setMovimento(novo.getMovimento());
        existente.setQtd(novo.getQtd());
        existente.setData(novo.getData());
        existente.setProduto(produto);

        return eisRepository.save(existente);
    }

    public void deleteById(Long id) {
        eisRepository.deleteById(id);
    }
}
