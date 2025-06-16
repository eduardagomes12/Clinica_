package com.example.core.services;

import com.example.core.models.Fatura;
import com.example.core.models.LinhaFatura;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.LinhaFaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinhaFaturaService {

    private final LinhaFaturaRepository linhaFaturaRepository;
    private final FaturaRepository faturaRepository;

    public LinhaFaturaService(LinhaFaturaRepository linhaFaturaRepository, FaturaRepository faturaRepository) {
        this.linhaFaturaRepository = linhaFaturaRepository;
        this.faturaRepository = faturaRepository;
    }

    public List<LinhaFatura> findAll() {
        return linhaFaturaRepository.findAll();
    }

    public Optional<LinhaFatura> findById(Long id) {
        return linhaFaturaRepository.findById(id);
    }

    public LinhaFatura save(LinhaFatura lf) {
        return linhaFaturaRepository.save(lf);
    }

    public LinhaFatura update(Long id, LinhaFatura updated) {
        LinhaFatura existing = linhaFaturaRepository.findById(id).orElseThrow();
        existing.setDescricao(updated.getDescricao());
        existing.setPrecoUnit(updated.getPrecoUnit());
        existing.setQtd(updated.getQtd());
        existing.setTotal(updated.getTotal());
        existing.setFatura(updated.getFatura());
        return linhaFaturaRepository.save(existing);
    }

    public void deleteById(Long id) {
        linhaFaturaRepository.deleteById(id);
    }

    public Fatura getFaturaById(Long id) {
        return faturaRepository.findById(id).orElseThrow();
    }
}
