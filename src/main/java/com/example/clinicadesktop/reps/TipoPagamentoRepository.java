package com.example.clinicadesktop.reps;

import com.example.clinicadesktop.models.Tipopagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<Tipopagamento, Long> {
}
