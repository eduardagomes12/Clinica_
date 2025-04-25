package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByConsultaId(Long consultaId);  // Método adicionado para buscar pagamentos de uma consulta
}
