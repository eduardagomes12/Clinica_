package com.example.backend.services;

import com.example.backend.DTO.PagamentoDTO;
import com.example.core.models.Fatura;
import com.example.core.models.Pagamento;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final FaturaRepository faturaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, FaturaRepository faturaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.faturaRepository = faturaRepository;
    }

    public List<PagamentoDTO> findAll() {
        return pagamentoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PagamentoDTO findById(Long id) {
        return pagamentoRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public PagamentoDTO save(PagamentoDTO dto) {
        Pagamento pagamento = toEntity(dto);
        return toDTO(pagamentoRepository.save(pagamento));
    }

    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow();
        pagamento.setValor(dto.getValor());
        pagamento.setData(dto.getData());
        pagamento.setFatura(faturaRepository.findById(dto.getIdFatura()).orElseThrow());
        return toDTO(pagamentoRepository.save(pagamento));
    }

    public void deleteById(Long id) {
        pagamentoRepository.deleteById(id);
    }

    private PagamentoDTO toDTO(Pagamento p) {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(p.getId());
        dto.setValor(p.getValor());
        dto.setData(p.getData());
        dto.setIdFatura(p.getFatura().getId());
        return dto;
    }

    private Pagamento toEntity(PagamentoDTO dto) {
        Pagamento p = new Pagamento();
        p.setId(dto.getId());
        p.setValor(dto.getValor());
        p.setData(dto.getData());
        p.setFatura(faturaRepository.findById(dto.getIdFatura()).orElseThrow());
        return p;
    }
}
