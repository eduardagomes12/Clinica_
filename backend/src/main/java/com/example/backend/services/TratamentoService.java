package com.example.backend.services;

import com.example.backend.DTO.TratamentoDTO;
import com.example.core.models.Consulta;
import com.example.core.models.TipoTratamento;
import com.example.core.models.Tratamento;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.TipoTratamentoRepository;
import com.example.core.reps.TratamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TratamentoService {

    private final TratamentoRepository tratamentoRepository;
    private final TipoTratamentoRepository tipoTratamentoRepository;
    private final ConsultaRepository consultaRepository;

    public TratamentoService(TratamentoRepository tratamentoRepository,
                             TipoTratamentoRepository tipoTratamentoRepository,
                             ConsultaRepository consultaRepository) {
        this.tratamentoRepository = tratamentoRepository;
        this.tipoTratamentoRepository = tipoTratamentoRepository;
        this.consultaRepository = consultaRepository;
    }

    public List<TratamentoDTO> findAll() {
        return tratamentoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TratamentoDTO findById(Long id) {
        return tratamentoRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public TratamentoDTO save(TratamentoDTO dto) {
        Tratamento t = toEntity(dto);
        return toDTO(tratamentoRepository.save(t));
    }

    public TratamentoDTO update(Long id, TratamentoDTO dto) {
        Tratamento t = tratamentoRepository.findById(id).orElseThrow();
        t.setTipo(dto.getTipo());
        t.setData(dto.getData());
        t.setDescricao(dto.getDescricao());
        t.setStatus(dto.getStatus());
        t.setTipoTratamento(tipoTratamentoRepository.findById(dto.getIdTipoTratamento()).orElseThrow());
        t.setConsulta(consultaRepository.findById(dto.getIdConsulta()).orElseThrow());
        return toDTO(tratamentoRepository.save(t));
    }

    public void deleteById(Long id) {
        tratamentoRepository.deleteById(id);
    }

    private TratamentoDTO toDTO(Tratamento t) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(t.getId());
        dto.setTipo(t.getTipo());
        dto.setData(t.getData());
        dto.setDescricao(t.getDescricao());
        dto.setStatus(t.getStatus());
        dto.setIdTipoTratamento(t.getTipoTratamento().getId());
        dto.setIdConsulta(t.getConsulta().getId());
        return dto;
    }

    private Tratamento toEntity(TratamentoDTO dto) {
        Tratamento t = new Tratamento();
        t.setId(dto.getId());
        t.setTipo(dto.getTipo());
        t.setData(dto.getData());
        t.setDescricao(dto.getDescricao());
        t.setStatus(dto.getStatus());
        t.setTipoTratamento(tipoTratamentoRepository.findById(dto.getIdTipoTratamento()).orElseThrow());
        t.setConsulta(consultaRepository.findById(dto.getIdConsulta()).orElseThrow());
        return t;
    }
}
