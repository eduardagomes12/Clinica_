package com.example.core.services;

import com.example.core.models.Fatura;
import com.example.core.models.Pagamento;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.PagamentoRepository;
import org.springframework.stereotype.Service;
import com.example.core.models.TipoPagamento;
import com.example.core.models.Consulta;
import com.example.core.reps.TipoPagamentoRepository;
import com.example.core.reps.ConsultaRepository;


import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final FaturaRepository faturaRepository;
    private final TipoPagamentoRepository tipoPagamentoRepository;
    private final ConsultaRepository consultaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            FaturaRepository faturaRepository,
                            TipoPagamentoRepository tipoPagamentoRepository,
                            ConsultaRepository consultaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.faturaRepository = faturaRepository;
        this.tipoPagamentoRepository = tipoPagamentoRepository;
        this.consultaRepository = consultaRepository;
    }

    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> findById(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento save(Pagamento p) {
        return pagamentoRepository.save(p);
    }

    public Pagamento update(Long id, Pagamento p) {
        Pagamento existing = pagamentoRepository.findById(id).orElseThrow();
        existing.setData(p.getData());
        existing.setValor(p.getValor());
        existing.setFatura(p.getFatura());
        return pagamentoRepository.save(existing);
    }

    public void deleteById(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public Fatura getFaturaById(Long id) {
        return faturaRepository.findById(id).orElseThrow();
    }

    public TipoPagamento getTipoPagamentoById(Long id) {
        return tipoPagamentoRepository.findById(id).orElseThrow();
    }

    public Consulta getConsultaById(Long id) {
        return consultaRepository.findById(id).orElseThrow();
    }
}
