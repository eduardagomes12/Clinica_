package com.example.clinicadesktop.services;

import com.example.clinicadesktop.models.Pagamento;
import com.example.clinicadesktop.reps.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public List<Pagamento> findAll() {
        return repository.findAll();
    }

    public Pagamento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pagamento save(Pagamento pagamento) {
        return repository.save(pagamento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Pagamento> findByConsultaId(Long consultaId) {  // Novo método para buscar pagamentos por consulta
        return repository.findByConsultaId(consultaId);
    }
}
