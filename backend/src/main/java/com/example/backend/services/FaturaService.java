package com.example.backend.services;

import com.example.backend.DTO.FaturaDTO;
import com.example.core.models.Fatura;
import com.example.core.models.TipoPagamento;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.TipoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturaService {

    private final FaturaRepository repository;
    private final TipoPagamentoRepository tipoPagamentoRepository;

    public FaturaService(FaturaRepository repository, TipoPagamentoRepository tipoPagamentoRepository) {
        this.repository = repository;
        this.tipoPagamentoRepository = tipoPagamentoRepository;
    }

    public List<FaturaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FaturaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    public FaturaDTO save(FaturaDTO dto) {
        Fatura f = toEntity(dto);
        return toDTO(repository.save(f));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private FaturaDTO toDTO(Fatura f) {
        FaturaDTO dto = new FaturaDTO();
        dto.setId(f.getId());
        dto.setNum(f.getNum());
        dto.setData(f.getData());
        dto.setValorTotal(f.getValorTotal());
        dto.setIdTipoPagamento(f.getTipoPagamento().getId());
        dto.setDescricaoTipoPagamento(f.getTipoPagamento().getDescricao());
        return dto;
    }

    private Fatura toEntity(FaturaDTO dto) {
        TipoPagamento tp = tipoPagamentoRepository.findById(dto.getIdTipoPagamento()).orElseThrow();
        Fatura f = new Fatura();
        f.setId(dto.getId());
        f.setNum(dto.getNum());
        f.setData(dto.getData());
        f.setValorTotal(dto.getValorTotal());
        f.setTipoPagamento(tp);
        return f;
    }
}
