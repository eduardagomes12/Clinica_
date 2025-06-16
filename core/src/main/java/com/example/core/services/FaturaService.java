package com.example.core.services;

import com.example.core.models.Fatura;
import com.example.core.models.TipoPagamento;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.TipoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FaturaService {

    private final FaturaRepository repository;
    private final TipoPagamentoRepository tipoPagamentoRepository;

    public FaturaService(FaturaRepository repository, TipoPagamentoRepository tipoPagamentoRepository) {
        this.repository = repository;
        this.tipoPagamentoRepository = tipoPagamentoRepository;
    }

    public List<Fatura> findAll() {
        return repository.findAll();
    }

    public Optional<Fatura> findById(Long id) {
        return repository.findById(id);
    }

    public Fatura save(Fatura f, Long idTipoPagamento) {
        TipoPagamento tp = tipoPagamentoRepository.findById(idTipoPagamento)
                .orElseThrow(() -> new NoSuchElementException("Tipo de pagamento não encontrado"));
        f.setTipoPagamento(tp);
        return repository.save(f);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
