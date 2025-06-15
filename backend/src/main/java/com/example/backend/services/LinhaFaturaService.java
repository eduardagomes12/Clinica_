package com.example.backend.services;

import com.example.backend.DTO.LinhaFaturaDTO;
import com.example.core.models.Fatura;
import com.example.core.models.LinhaFatura;
import com.example.core.reps.FaturaRepository;
import com.example.core.reps.LinhaFaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinhaFaturaService {

    private final LinhaFaturaRepository linhaFaturaRepository;
    private final FaturaRepository faturaRepository;

    public LinhaFaturaService(LinhaFaturaRepository linhaFaturaRepository, FaturaRepository faturaRepository) {
        this.linhaFaturaRepository = linhaFaturaRepository;
        this.faturaRepository = faturaRepository;
    }

    public List<LinhaFaturaDTO> findAll() {
        return linhaFaturaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LinhaFaturaDTO findById(Long id) {
        return linhaFaturaRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public LinhaFaturaDTO save(LinhaFaturaDTO dto) {
        LinhaFatura lf = toEntity(dto);
        return toDTO(linhaFaturaRepository.save(lf));
    }

    public LinhaFaturaDTO update(Long id, LinhaFaturaDTO dto) {
        LinhaFatura lf = linhaFaturaRepository.findById(id).orElseThrow();
        lf.setDescricao(dto.getDescricao());
        lf.setPrecoUnit(dto.getPrecoUnit());
        lf.setQtd(dto.getQtd());
        lf.setTotal(dto.getTotal());
        lf.setFatura(faturaRepository.findById(dto.getIdFatura()).orElseThrow());
        return toDTO(linhaFaturaRepository.save(lf));
    }

    public void deleteById(Long id) {
        linhaFaturaRepository.deleteById(id);
    }

    private LinhaFaturaDTO toDTO(LinhaFatura lf) {
        LinhaFaturaDTO dto = new LinhaFaturaDTO();
        dto.setId(lf.getId());
        dto.setDescricao(lf.getDescricao());
        dto.setPrecoUnit(lf.getPrecoUnit());
        dto.setQtd(lf.getQtd());
        dto.setTotal(lf.getTotal());
        dto.setIdFatura(lf.getFatura().getId());
        return dto;
    }

    private LinhaFatura toEntity(LinhaFaturaDTO dto) {
        Fatura fatura = faturaRepository.findById(dto.getIdFatura()).orElseThrow();
        LinhaFatura lf = new LinhaFatura();
        lf.setId(dto.getId());
        lf.setDescricao(dto.getDescricao());
        lf.setPrecoUnit(dto.getPrecoUnit());
        lf.setQtd(dto.getQtd());
        lf.setTotal(dto.getTotal());
        lf.setFatura(fatura);
        return lf;
    }
}
