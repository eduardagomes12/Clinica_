package com.example.core.reps;

import com.example.core.models.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {
}
