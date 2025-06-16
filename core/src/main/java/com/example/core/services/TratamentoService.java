package com.example.core.services;

import com.example.core.models.Consulta;
import com.example.core.models.TipoTratamento;
import com.example.core.models.Tratamento;
import com.example.core.reps.ConsultaRepository;
import com.example.core.reps.TipoTratamentoRepository;
import com.example.core.reps.TratamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Tratamento> findAll() {
        return tratamentoRepository.findAll();
    }

    public Optional<Tratamento> findById(Long id) {
        return tratamentoRepository.findById(id);
    }

    public Tratamento save(Tratamento t) {
        return tratamentoRepository.save(t);
    }

    public Tratamento update(Long id, Tratamento updated) {
        Tratamento t = tratamentoRepository.findById(id).orElseThrow();
        t.setTipo(updated.getTipo());
        t.setData(updated.getData());
        t.setDescricao(updated.getDescricao());
        t.setStatus(updated.getStatus());
        t.setTipoTratamento(updated.getTipoTratamento());
        t.setConsulta(updated.getConsulta());
        return tratamentoRepository.save(t);
    }

    public void deleteById(Long id) {
        tratamentoRepository.deleteById(id);
    }

    public TipoTratamento getTipoTratamentoById(Long id) {
        return tipoTratamentoRepository.findById(id).orElseThrow();
    }

    public Consulta getConsultaById(Long id) {
        return consultaRepository.findById(id).orElseThrow();
    }
}
